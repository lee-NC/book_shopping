package com.example.book_shopping.controller;

import com.example.book_shopping.request.SignInRequest;
import com.example.book_shopping.request.SignUpRequest;
import com.example.book_shopping.request.UpdateUserRequest;
import com.example.book_shopping.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author lengo
 * created on 3/18/2022
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    private final UserService service;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/sign_in")
    public ResponseEntity<Object> signIn(@Valid @RequestBody SignInRequest request) {
        return ResponseEntity.ok(service.signIn(request));
    }

    @PostMapping("/sign_out/{userId}")
    public ResponseEntity<Object> signOut(@PathVariable("userId") int userId) {
        if (service.signOut(userId)) {
            return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
        }
        return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

    @PostMapping("/sign_up")
    public ResponseEntity<Object> signUp(@Valid @RequestBody SignUpRequest request) {
        return ResponseEntity.ok(service.signUp(request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserDetail(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(service.get(userId));
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllUser() {
        return ResponseEntity.ok(service.getAllUser());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable("userId") int userId, @Valid @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(service.update(userId, request));
    }

    @PutMapping("/status/{userId}")
    public ResponseEntity<Object> changeStatus(@PathVariable("userId") int userId) {
        return service.changeActive(userId) ? ResponseEntity.ok(HttpStatus.OK.getReasonPhrase()) : ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }
}
