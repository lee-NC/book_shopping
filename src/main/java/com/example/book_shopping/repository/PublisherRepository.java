package com.example.book_shopping.repository;

import com.example.book_shopping.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lengo
 * created on 3/18/2022
 */
@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    boolean existsByName(String name);
}
