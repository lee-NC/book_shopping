package com.example.book_shopping.controller;

import com.example.book_shopping.request.CreateCartRequest;
import com.example.book_shopping.request.UpdateCartRequest;
import com.example.book_shopping.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author lengo
 * created on 3/20/2022
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/carts")
public class CartController {
    private final CartService service;
    Logger logger = LoggerFactory.getLogger(CartController.class);

    public CartController(CartService service) {
        this.service = service;
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<Object> getAllCartByUserId(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(service.getAllByUser(userId));
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllUser() {
        return ResponseEntity.ok(service.getAllCart());
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Object> addCartByUserId(@PathVariable("userId") int userId, @Valid @RequestBody CreateCartRequest request) {
        return service.add(userId, request) ? ResponseEntity.ok(HttpStatus.OK.getReasonPhrase()) : ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<Object> updateCart(@PathVariable("cartId") int cartId, @Valid @RequestBody UpdateCartRequest request) {
        return ResponseEntity.ok(service.update(cartId, request));
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Object> deleteCart(@PathVariable("cartId") int cartId) {
        return service.delete(cartId) ? ResponseEntity.ok(HttpStatus.OK.getReasonPhrase()) : ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }
}
