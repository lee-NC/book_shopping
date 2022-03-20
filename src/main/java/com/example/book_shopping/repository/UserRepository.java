package com.example.book_shopping.repository;

import com.example.book_shopping.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lengo
 * created on 3/18/2022
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmailAndPasswordAndIsActive(String email, String password, boolean isActive);

    boolean existsByEmail(String email);

    User findByIdAndIsActiveAndIsAdmin(int id, boolean isActive, boolean isAdmin);

    User findByIdAndIsAdmin(int userId, boolean isAdmin);
}
