package com.example.book_shopping.controller;

import com.example.book_shopping.entity.DefineString;
import com.example.book_shopping.request.CreateOrderRequest;
import com.example.book_shopping.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

/**
 * @author lengo
 * created on 3/20/2022
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getAllOrderByUserAndStatus(@PathVariable("userId") int userId, @RequestParam(value = "status", defaultValue = "SUBMITTING") String status) {
        status = status.trim().toUpperCase(Locale.ROOT);
        return status.equals(DefineString.DELIVERED) || status.equals(DefineString.SUBMITTING) || status.equals(DefineString.DELIVERING) ? ResponseEntity.ok(service.getAllByStatus(userId, status)) : ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllOrder() {
        return ResponseEntity.ok(service.getAllOrder());
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Object> addOrder(@PathVariable("userId") int userId, @Valid @RequestBody CreateOrderRequest request) {
        return ResponseEntity.ok(service.add(userId, request));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Object> updateOrder(@PathVariable("orderId") int orderId, @RequestParam(value = "addressId") int addressId) {
        return ResponseEntity.ok(service.update(orderId, addressId));
    }

    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<Object> cancelOrder(@PathVariable("orderId") int orderId) {
        return service.cancel(orderId) ? ResponseEntity.ok(HttpStatus.OK.getReasonPhrase()) : ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }
}
