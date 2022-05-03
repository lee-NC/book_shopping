package com.example.book_shopping.repository;

import com.example.book_shopping.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lengo
 * created on 5/3/2022
 */
@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
    ProductImage findByName(String name);
}
