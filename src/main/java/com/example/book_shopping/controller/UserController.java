package com.example.book_shopping.controller;

import com.example.book_shopping.request.SignInRequest;
import com.example.book_shopping.request.SignUpRequest;
import com.example.book_shopping.request.UpdateUserRequest;
import com.example.book_shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author lengo
 * created on 3/18/2022
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/sign_in")
    public ResponseEntity<Object> signIn(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(userService.signIn(request));
    }

    @PostMapping("/sign_out/{userId}")
    public ResponseEntity<Object> signIn(@PathVariable("userId") int userId) {
        if (userService.signOut(userId)) {
            return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
        }
        return ResponseEntity.ok(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

    @PostMapping("/sign_up")
    public ResponseEntity<Object> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(userService.signUp(request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserDetail(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(userService.get(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable("userId") int userId, @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(userService.update(userId, request));
    }
}
