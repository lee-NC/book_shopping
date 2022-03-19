package com.example.book_shopping.controller;

import com.example.book_shopping.request.AddressRequest;
import com.example.book_shopping.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author lengo
 * created on 3/19/2022
 */
public class AddressController {
    private AddressService service;


    @PutMapping("/{addressId}")
    public ResponseEntity<Object> updateAddress(@PathVariable("addressId") int addressId, @RequestBody AddressRequest request) {
        return ResponseEntity.ok(service.update(addressId, request));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Object> addAddress(@PathVariable("userId") int userId, @RequestBody AddressRequest request) {
        return ResponseEntity.ok(service.add(userId, request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getAllAddress(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(service.getAll(userId));
    }

    @PutMapping("/main/{addressId}")
    public ResponseEntity<Object> setMain(@PathVariable("addressId") int addressId) {
        return ResponseEntity.ok(service.setMain(addressId));
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Object> deleteAddress(@PathVariable("addressId") int addressId) {
        if (service.delete(addressId)) return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

}
