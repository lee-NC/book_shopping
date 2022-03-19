package com.example.book_shopping.controller;

import com.example.book_shopping.request.CreateCategoryRequest;
import com.example.book_shopping.request.StringRequest;
import com.example.book_shopping.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author lengo
 * created on 3/18/2022
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService service;
    Logger logger = LoggerFactory.getLogger(CategoryController.class);

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Object> getCategory(@PathVariable("categoryId") int categoryId) {
        return ResponseEntity.ok(service.get(categoryId));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Object> updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody StringRequest request) {
        return ResponseEntity.ok(service.update(categoryId, request));
    }

    @PostMapping("")
    public ResponseEntity<Object> addCategory(@RequestBody CreateCategoryRequest request) {
        return ResponseEntity.ok(service.add(request));
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllCategory() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Object> deleteCategory(@PathVariable("categoryId") int categoryId) {
        if (service.delete(categoryId)) return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }
}
