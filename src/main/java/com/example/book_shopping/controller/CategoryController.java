package com.example.book_shopping.controller;

import com.example.book_shopping.request.CreateCategoryRequest;
import com.example.book_shopping.request.UpdateCategoryRequest;
import com.example.book_shopping.service.CategoryService;
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
    private CategoryService categoryService;

    @GetMapping("/{categoryId}")
    public ResponseEntity<Object> getCategory(@PathVariable("categoryId") int categoryId) {
        return ResponseEntity.ok(categoryService.get(categoryId));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Object> updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody UpdateCategoryRequest request) {
        return ResponseEntity.ok(categoryService.update(categoryId, request));
    }

    @PostMapping("")
    public ResponseEntity<Object> addCategory(@RequestBody CreateCategoryRequest request) {
        return ResponseEntity.ok(categoryService.add(request));
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllCategory() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Object> deleteCategory(@PathVariable("categoryId") int categoryId) {
        if (categoryService.delete(categoryId)) return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }
}
