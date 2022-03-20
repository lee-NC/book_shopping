package com.example.book_shopping.repository;

import com.example.book_shopping.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lengo
 * created on 3/20/2022
 */
@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {
}
