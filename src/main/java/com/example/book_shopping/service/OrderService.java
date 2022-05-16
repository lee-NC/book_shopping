package com.example.book_shopping.service;

import com.example.book_shopping.entity.*;
import com.example.book_shopping.exception.BadRequestException;
import com.example.book_shopping.exception.NotFoundException;
import com.example.book_shopping.repository.*;
import com.example.book_shopping.request.CreateOrderRequest;
import com.example.book_shopping.request.CreateProductOrderRequest;
import com.example.book_shopping.response.ListOderResponse;
import com.example.book_shopping.response.OrderProductResponse;
import com.example.book_shopping.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * @author lengo
 * created on 3/20/2022
 */
@Service
public class OrderService {
    Locale locale = new Locale("vi", "VN");
    DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    public OrderResponse add(int userId, CreateOrderRequest request) {
        try {
            User user = userRepository.findByIdAndIsActiveAndIsAdmin(userId, true, false);
            // Optional<Address> address = addressRepository.findById(request.getAddressId());
            if (user != null) {
                Set<OrderProduct> orderProducts = new HashSet<>();
                double value = 0;
                Order order = new Order();
                order.setStatus(DefineString.SUBMITTING);
                order.setAddress(null);
                order.setValue(value);
                order.setAddressDesc(request.getAddress());
                order.setEmail(request.getEmail());
                order.setName(request.getName());
                order.setPhoneNumber(request.getPhoneNumber());
                order = orderRepository.save(order);
                for (CreateProductOrderRequest id : request.getCartIds()) {
                    Optional<Product> product = productRepository.findById(id.getProductId());
                    if (product.isPresent() && product.get().getAmount() >= id.getAmountProduct() && product.get().isActive()) {
                        OrderProduct orderProduct = new OrderProduct();
                        orderProduct.setAmount(product.get().getAmount());
                        orderProduct.setProduct(product.get());
                        orderProduct.setOrder(order);
                        orderProduct = orderProductRepository.save(orderProduct);
                        orderProducts.add(orderProduct);
                        value += product.get().getPrice() * product.get().getAmount();
                        // Product product = product.get().getProduct();
                        product.get().setAmount(product.get().getAmount() - product.get().getAmount());
                        productRepository.save(product.get());
                    }
                }
                order.setValue(value);
                order.setOrderProducts(orderProducts);
                order = orderRepository.save(order);
                return toOrderResponse(order);
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    // public OrderResponse update(int orderId, int addressId) {
    //     try {
    //         Optional<Address> address = addressRepository.findById(addressId);
    //         Order order = orderRepository.findByIdAndStatus(orderId, DefineString.SUBMITTING);
    //         if (address.isPresent() && order != null && !address.get().equals(order.getAddress()) && address.get().getUser().equals(order.getAddress().getUser())) {
    //             order.setAddress(address.get());
    //             order = orderRepository.save(order);
    //             return toOrderResponse(order);
    //         }
    //         throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         throw new BadRequestException(e.getMessage());
    //     }
    // }

    public List<Order> getAllOrder() {
        try {
            return orderRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public boolean cancel(int orderId) {
        try {
            Order order = orderRepository.findByIdAndStatus(orderId, DefineString.SUBMITTING);
            if (order != null) {
                order.setStatus(DefineString.CANCELLED);
                orderRepository.save(order);
                for (OrderProduct orderProduct : order.getOrderProducts()) {
                    Product product = orderProduct.getProduct();
                    product.setAmount(product.getAmount() + orderProduct.getAmount());
                    productRepository.save(product);
                }
                return true;
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public ListOderResponse getAllByStatus(int userId, String status) {
        try {
            User user = userRepository.findByIdAndIsActiveAndIsAdmin(userId, true, false);
            if (user != null) {
                List<Address> addresses = addressRepository.findAllByUser(user);
                List<Order> orders = new ArrayList<>();
                for (Address address : addresses) {
                    orders.addAll(orderRepository.findAllByAddressAndStatus(address, status));
                }
                return new ListOderResponse(userId, toOrderResponses(orders));
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public void acceptOrder() {
        try {
            List<Order> orders = orderRepository.findAllByStatus(DefineString.SUBMITTING);
            if (!orders.isEmpty()) {
                for (Order order : orders) {
                    if (order.getUpdatedAt().getTime() <= (new Date().getTime() + 600000)) {
                        order.setStatus(DefineString.DELIVERING);
                        orderRepository.save(order);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public void delivered() {
        try {
            List<Order> orders = orderRepository.findAllByStatus(DefineString.DELIVERING);
            if (!orders.isEmpty()) {
                for (Order order : orders) {
                    if (order.getUpdatedAt().after(new Date()) && order.getUpdatedAt().compareTo(new Date()) == 2) {
                        order.setStatus(DefineString.DELIVERED);
                        orderRepository.save(order);
                    }
                }
            }
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
        response.setPrice(product.getPrice());//revert to VND
        response.setName(product.getName());
        response.setPublisherName(product.getPublisher().getName());
        response.setAmount(orderProduct.getAmount());
        return response;
    }

    private OrderResponse toOrderResponse(Order order) {
        OrderResponse response = new OrderResponse();
        // response.setAddressId(order.getAddress().getId());
        // response.setAddress(order.getAddress().getAddressDetail());
        // response.setAddressDesc(order.getAddress().getDescription());
        response.setName(order.getName());
        response.setEmail(order.getEmail());
        response.setPhoneNumber(order.getPhoneNumber());
        response.setAddressDesc(order.getAddressDesc());
        response.setStatus(order.getStatus());
        response.setCreateAt(order.getCreatedAt());
        response.setValue(order.getValue());//revert to VND
        List<OrderProductResponse> productResponses = new ArrayList<>();
        for (OrderProduct orderProduct : order.getOrderProducts()) {
            productResponses.add(toProductOrderResponse(orderProduct));
        }
        response.setProductResponses(productResponses);
        return response;
    }

    private List<OrderResponse> toOrderResponses(List<Order> orders) {
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orders) {
            orderResponses.add(toOrderResponse(order));
        }
        return orderResponses;
    }
}
