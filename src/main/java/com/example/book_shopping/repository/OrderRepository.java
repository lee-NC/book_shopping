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
    Order findByIdAndStatus(int id, String status);

    List<Order> findAllByAddressAndStatus(Address address, String status);

    List<Order> findAllByStatus(String status);
}
