package com.example.book_shopping.service;

import com.example.book_shopping.entity.Cart;
import com.example.book_shopping.entity.Product;
import com.example.book_shopping.entity.User;
import com.example.book_shopping.exception.BadRequestException;
import com.example.book_shopping.exception.NotFoundException;
import com.example.book_shopping.repository.CartRepository;
import com.example.book_shopping.repository.ProductRepository;
import com.example.book_shopping.repository.UserRepository;
import com.example.book_shopping.request.CreateCartRequest;
import com.example.book_shopping.request.UpdateCartRequest;
import com.example.book_shopping.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * @author lengo
 * created on 3/20/2022
 */
@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public ListCartResponse getAllByUser(int userId) {
        try {
            User user = userRepository.findByIdAndIsActiveAndIsAdmin(userId, true, false);
            if (user != null) {
                List<Cart> carts = cartRepository.findAllByUserGroupByPublisherOrderByUpdatedAt(user);
                if (carts == null) throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
                List<CartResponse> cartResponses = new ArrayList<>();
                for (Cart cart : carts) {
                    cartResponses.add(toCartResponse(cart));
                }
                return new ListCartResponse(userId, cartResponses);
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public boolean add(int userId, CreateCartRequest request) {
        try {
            User user = userRepository.findByIdAndIsActiveAndIsAdmin(userId, true, false);
            Product product = productRepository.findByIdAndIsActive(request.getProductId(), true);
            System.out.println("Done");
            if (user != null && request.getAmount() <= product.getAmount()){
                Cart data = cartRepository.findByUserAndProduct(user, product);
                if (data!=null){
                    if ((data.getAmount() + request.getAmount()) <= product.getAmount()){
                        data.setAmount(data.getAmount() + request.getAmount());
                        cartRepository.save(data);
                    }
                    else throw new BadRequestException("Products to the limit");
                }
                else {
                    Cart cart = new Cart();
                    cart.setAmount(request.getAmount());
                    cart.setProduct(product);
                    cart.setUser(user);
                    cartRepository.save(cart);
                }
                return true;
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<Cart> getAllCart() {
        try {
            return cartRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public boolean delete(int id) {
        try {
            Optional<Cart> cart = cartRepository.findById(id);
            if (cart.isPresent()){
                cartRepository.delete(cart.get());
                return true;
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public CartResponse update(int id, UpdateCartRequest request) {
        try {
            Optional<Cart> cart = cartRepository.findById(id);
            if (cart.isPresent()){
                Cart data = cart.get();
                if (data.getProduct().isActive() && request.getAmount()<= data.getProduct().getAmount() ){
                    data.setAmount(request.getAmount());
                    data = cartRepository.save(data);
                    return toCartResponse(data);
                }
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    private ProductCartResponse toProductCartResponse(Product product) {
        ProductCartResponse response = new ProductCartResponse();
        response.setId(product.getId());
        response.setActive(product.isActive());
        Locale locale = new Locale("vi", "VN");
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        response.setPrice(decimalFormat.format(product.getPrice()) + " đ");//revert to VND
        response.setAmountAvailable(product.getAmount());
        response.setName(product.getName());
        response.setPublisherName(product.getPublisher().getName());
        response.setPublisherId(product.getPublisher().getId());
        response.setPublisherId(product.getPublisher().getId());
        return response;
    }

    private CartResponse toCartResponse(Cart cart) {
        CartResponse response = new CartResponse();
        response.setAmount(cart.getAmount());
        response.setId(cart.getId());
        response.setProductResponse(toProductCartResponse(cart.getProduct()));
        return response;
    }

}
