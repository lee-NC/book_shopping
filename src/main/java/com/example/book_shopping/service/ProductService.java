package com.example.book_shopping.service;

import com.example.book_shopping.entity.Product;
import com.example.book_shopping.exception.BadRequestException;
import com.example.book_shopping.exception.NotFoundException;
import com.example.book_shopping.repository.ProductRepository;
import com.example.book_shopping.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author lengo
 * created on 3/19/2022
 */
@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductResponse get(int id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if (product.isPresent()) {
                return toProductResponse(product.get());
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());

        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    private ProductResponse toProductResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setDesc(product.getDescription());
        response.setName(product.getName());
        response.setActive(product.isActive());
        response.setAmount(product.getAmount());
        response.setPrice(product.getPrice());
        response.setLanguage(product.getLanguage());
        response.setPublishingYear(product.getPublishingYear());
        response.setCategory(product.getCategory());
        response.setProcedure(product.getProcedure());
        return response;
    }

}
