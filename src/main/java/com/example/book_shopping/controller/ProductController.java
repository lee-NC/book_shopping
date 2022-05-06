package com.example.book_shopping.controller;
import com.example.book_shopping.request.CreateProductRequest;
import com.example.book_shopping.request.UpdateProductRequest;
import com.example.book_shopping.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

    @GetMapping("")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/best_sale")
    public ResponseEntity<Object> getBestSale() {
        return ResponseEntity.ok(service.getBestSale());
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

    @GetMapping("/publisherId/{publisherIdId}")
    public ResponseEntity<Object> getProductByPublisherId(@PathVariable("publisherIdId") int publisherIdId) {
        return ResponseEntity.ok(service.getAllByPublisherId(publisherIdId));
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
    public ResponseEntity<Object> updateProduct(@PathVariable("productId") int productId, @Valid @RequestBody UpdateProductRequest request) {
        return ResponseEntity.ok(service.update(productId, request));
    }

    @PostMapping("/image/upload/{id}")
    public ResponseEntity<Object> uploadImage(@RequestParam("file") MultipartFile[] files, @PathVariable("id") int id) {
        return ResponseEntity.ok(service.uploadMultipleImages(files, id));
    }

    @GetMapping("/image/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String fileName, HttpServletRequest request) {
        return service.downloadImage(fileName, request);
    }

    @PostMapping("")
    public ResponseEntity<Object> addProduct(@Valid @RequestBody CreateProductRequest request) {
        return ResponseEntity.ok(service.add(request));
    }

    @PutMapping("/status/{productId}")
    public ResponseEntity<Object> changeStatus(@PathVariable("productId") int productId) {
        return ResponseEntity.ok(service.changeStatus(productId));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("productId") int productId) {
        return service.delete(productId) ? ResponseEntity.ok(HttpStatus.OK.getReasonPhrase()) : ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

    @DeleteMapping("/image")
    public ResponseEntity<Object> deleteProductImage(@RequestParam(value = "name") String name) {
        return service.deleteImage(name) ? ResponseEntity.ok(HttpStatus.OK.getReasonPhrase()) : ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }
}
