package com.example.book_shopping.repository;

import com.example.book_shopping.entity.Cart;
import com.example.book_shopping.entity.Product;
import com.example.book_shopping.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lengo
 * created on 3/18/2022
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query(value = "SELECT c FROM carts c " +
            "WHERE c.user = ?1 " +
            "GROUP BY c.product.publisher " +
            "ORDER BY c.updatedAt",
            countQuery = "SELECT count(c) FROM carts c")
    List<Cart> findAllByUserGroupByPublisherOrderByUpdatedAt(User user);

    Cart findByUserAndProduct(User user, Product product);
}
