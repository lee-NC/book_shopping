package com.example.book_shopping.repository;

import com.example.book_shopping.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author lengo
 * created on 3/18/2022
 */
@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
