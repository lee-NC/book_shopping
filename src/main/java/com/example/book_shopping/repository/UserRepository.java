package com.example.book_shopping.repository;

import com.example.book_shopping.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author lengo
 * created on 3/18/2022
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmailAndPasswordAndIsActive(String email, String password, boolean isActive);

    boolean existsByEmail(String email);

    User findByIdAndIsActive(int id, boolean isActive);
}
