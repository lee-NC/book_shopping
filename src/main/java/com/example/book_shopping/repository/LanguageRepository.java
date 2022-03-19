package com.example.book_shopping.repository;

import com.example.book_shopping.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lengo
 * created on 3/19/2022
 */
@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
    boolean existsByName(String name);
}
