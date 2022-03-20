package com.example.book_shopping.controller;

import com.example.book_shopping.request.AddressRequest;
import com.example.book_shopping.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author lengo
 * created on 3/19/2022
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/addresses")
public class AddressController {
    private final AddressService service;
    Logger logger = LoggerFactory.getLogger(AddressController.class);

    public AddressController(AddressService service) {
        this.service = service;
    }


    @PutMapping("/{addressId}")
    public ResponseEntity<Object> updateAddress(@PathVariable("addressId") int addressId, @Valid @RequestBody AddressRequest request) {
        return ResponseEntity.ok(service.update(addressId, request));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Object> addAddress(@PathVariable("userId") int userId, @Valid @RequestBody AddressRequest request) {
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
        return service.delete(addressId) ? ResponseEntity.ok(HttpStatus.OK.getReasonPhrase()) : ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

}
