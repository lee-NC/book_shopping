package com.example.book_shopping.repository;

import com.example.book_shopping.entity.OrderProduct;
import com.example.book_shopping.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lengo
 * created on 3/20/2022
 */
@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {
    @Query(value = "SELECT op.product, SUM (op.amount) FROM order_products op " +
            "INNER JOIN products p on op.product = p " +
            "GROUP BY p.id " +
            "ORDER BY SUM (op.amount) DESC ")
    List<Product> findProductBestSales();
}
