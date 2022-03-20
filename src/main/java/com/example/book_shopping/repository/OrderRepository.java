package com.example.book_shopping.repository;

import com.example.book_shopping.entity.Address;
import com.example.book_shopping.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lengo
 * created on 3/18/2022
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByAddress(Address address);
}
