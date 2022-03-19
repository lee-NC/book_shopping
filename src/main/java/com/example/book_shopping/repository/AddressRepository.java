package com.example.book_shopping.repository;

import com.example.book_shopping.entity.Address;
import com.example.book_shopping.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lengo
 * created on 3/18/2022
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findAllByUser(User user);
}
