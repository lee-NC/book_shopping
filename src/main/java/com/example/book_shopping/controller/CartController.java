package com.example.book_shopping.controller;

import com.example.book_shopping.request.CreateCartRequest;
import com.example.book_shopping.request.UpdateCartRequest;
import com.example.book_shopping.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author lengo
 * created on 3/20/2022
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;
    Logger logger = LoggerFactory.getLogger(CartController.class);

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<Object> getAllCartByUserId(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(cartService.getAllByUser(userId));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Object> addCartByUserId(@PathVariable("userId") int userId, @RequestBody CreateCartRequest request) {
        return cartService.add(userId, request) ? ResponseEntity.ok(HttpStatus.OK.getReasonPhrase()) : ResponseEntity.ok(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

    @PostMapping("/{cartId}")
    public ResponseEntity<Object> updateCart(@PathVariable("cartId") int cartId, @RequestBody UpdateCartRequest request) {
        return ResponseEntity.ok(cartService.update(cartId, request));
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Object> deleteCart(@PathVariable("cartId") int cartId) {
        return cartService.delete(cartId) ? ResponseEntity.ok(HttpStatus.OK.getReasonPhrase()) : ResponseEntity.ok(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }
}
