package com.example.book_shopping.service;

import com.example.book_shopping.entity.*;
import com.example.book_shopping.exception.BadRequestException;
import com.example.book_shopping.exception.NotFoundException;
import com.example.book_shopping.repository.AddressRepository;
import com.example.book_shopping.repository.OrderRepository;
import com.example.book_shopping.repository.UserRepository;
import com.example.book_shopping.response.*;
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
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    Locale locale = new Locale("vi", "VN");
    DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);

    public ListOderResponse getAllSubmit(int userId){
        try {
            User user = userRepository.findByIdAndIsActiveAndIsAdmin(userId, true, false);
            if (user != null) {
                List<Address> addresses = addressRepository.findAllByUser(user);
                List<Order> orders= new ArrayList<>();
                for (Address address: addresses){
                    orders.addAll(orderRepository.findAllByAddress(address));
                }
                return new ListOderResponse(userId, toOrderResponses(orders));
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    private OrderProductResponse toProductOrderResponse(OrderProduct orderProduct) {
        OrderProductResponse response = new OrderProductResponse();
        Product product = orderProduct.getProduct();
        response.setId(product.getId());
        response.setActive(product.isActive());
        response.setPrice(decimalFormat.format(product.getPrice()) + " đ");//revert to VND
        response.setName(product.getName());
        response.setProcedureName(product.getProcedure().getName());
        response.setAmount(orderProduct.getAmount());
        return response;
    }

    private List<OrderResponse> toOrderResponses(List<Order> orders){
        List<OrderResponse> orderResponses= new ArrayList<>();
        for (Order order: orders){
            OrderResponse response = new OrderResponse();
            response.setAddressId(order.getAddress().getId());
            response.setAddress(order.getAddress().getAddressDetail());
            response.setAddressDesc(order.getAddress().getDescription());
            response.setStatus(order.getStatus());
            response.setValue(decimalFormat.format(order.getValue())+ " đ");//revert to VND
            List<OrderProductResponse> productResponses = new ArrayList<>();
            for (OrderProduct orderProduct: order.getOrderProducts()){
                productResponses.add(toProductOrderResponse(orderProduct));
            }
            response.setProductResponses(productResponses);
            orderResponses.add(response);
        }
        return orderResponses;
    }
}
