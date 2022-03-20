package com.example.book_shopping.controller;

import com.example.book_shopping.request.CreateProductRequest;
import com.example.book_shopping.request.UpdateProductRequest;
import com.example.book_shopping.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author lengo
 * created on 3/19/2022
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;
    Logger logger = LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/price")
    public ResponseEntity<Object> getProductByPrice(@RequestParam(value = "greater", required = false, defaultValue = "100000000") String greater,
                                                    @RequestParam(value = "less", required = false, defaultValue = "0") String less) {
        return ResponseEntity.ok(service.getAllByPrice(Double.parseDouble(greater), Double.parseDouble(less)));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Object> getProductByCategoryId(@PathVariable("categoryId") int categoryId) {
        return ResponseEntity.ok(service.getAllByCategoryId(categoryId));
    }

    @GetMapping("/procedure/{procedureId}")
    public ResponseEntity<Object> getProductByProcedureId(@PathVariable("procedureId") int procedureId) {
        return ResponseEntity.ok(service.getAllByProcedureId(procedureId));
    }

    @GetMapping("/language/{languageId}")
    public ResponseEntity<Object> getProductByLanguageId(@PathVariable("languageId") int languageId) {
        return ResponseEntity.ok(service.getAllByLanguageId(languageId));
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchProduct(@RequestParam(value = "keyword") String keyword) {
        return ResponseEntity.ok(service.search(keyword));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Object> getProduct(@PathVariable("productId") int productId) {
        return ResponseEntity.ok(service.get(productId));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Object> updateProduct(@PathVariable("productId") int productId, @RequestBody UpdateProductRequest request) {
        return ResponseEntity.ok(service.update(productId, request));
    }

    @PostMapping("")
    public ResponseEntity<Object> addProduct(@RequestBody CreateProductRequest request) {
        return ResponseEntity.ok(service.add(request));
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllProduct() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/status/{productId}")
    public ResponseEntity<Object> changeStatus(@PathVariable("productId") int productId) {
        return ResponseEntity.ok(service.changeStatus(productId));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("productId") int productId) {
        return service.delete(productId) ? ResponseEntity.ok(HttpStatus.OK.getReasonPhrase()) : ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }
}
