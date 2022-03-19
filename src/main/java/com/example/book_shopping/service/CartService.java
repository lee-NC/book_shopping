package com.example.book_shopping.service;

import com.example.book_shopping.entity.Cart;
import com.example.book_shopping.entity.Product;
import com.example.book_shopping.entity.User;
import com.example.book_shopping.exception.BadRequestException;
import com.example.book_shopping.exception.NotFoundException;
import com.example.book_shopping.repository.CartRepository;
import com.example.book_shopping.repository.ProductRepository;
import com.example.book_shopping.repository.UserRepository;
import com.example.book_shopping.response.CartResponse;
import com.example.book_shopping.response.ListCartResponse;
import com.example.book_shopping.response.ProductCartResponse;
import com.example.book_shopping.response.UserCartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    public ListCartResponse getAll(int userId) {
        try {
            User user = userRepository.findByIdAndIsActive(userId, true);
            if (user != null) {
                List<Cart> carts = cartRepository.findAllByUserOrderByProcedureId(user);
                if (carts == null) throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
                List<CartResponse> cartResponses = new ArrayList<>();
                for (Cart cart : carts) {
                    cartResponses.add(toCartResponse(cart));
                }
                return new ListCartResponse(toUserCartResponse(user), cartResponses);
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    private UserCartResponse toUserCartResponse(User user) {
        String fullName = user.getLastName() + " " + user.getFirstName();
        return new UserCartResponse(user.getId(), fullName);
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
        response.setProcedureName(product.getProcedure().getName());
        return response;
    }

    private CartResponse toCartResponse(Cart cart) {
        CartResponse response = new CartResponse();
        response.setAmount(cart.getAmount());
        response.setId(cart.getId());
        response.setProductCartResponse(toProductCartResponse(cart.getProduct()));
        return response;
    }

}
