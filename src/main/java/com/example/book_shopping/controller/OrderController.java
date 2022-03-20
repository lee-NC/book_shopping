package com.example.book_shopping.controller;

import com.example.book_shopping.entity.DefineString;
import com.example.book_shopping.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

/**
 * @author lengo
 * created on 3/20/2022
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/addresses")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getAllOrderByUserAndStatus(@PathVariable("userId") int userId, @RequestParam(value = "status", defaultValue = "SUBMITTING") String status) {
        status = status.trim().toUpperCase(Locale.ROOT);
        if (status.equals(DefineString.DELIVERED) && status.equals(DefineString.SUBMITTING) && status.equals(DefineString.DELIVERING)){
            return ResponseEntity.ok(service.getAllByStatus(userId, status));
        }
        return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }
}
