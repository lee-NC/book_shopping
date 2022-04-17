package com.example.book_shopping.repository;

import com.example.book_shopping.entity.Category;
import com.example.book_shopping.entity.Language;
import com.example.book_shopping.entity.Publisher;
import com.example.book_shopping.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lengo
 * created on 3/18/2022
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByIdAndIsActive(int id, boolean isActive);

    List<Product> findAllByCategory(Category category);

    List<Product> findAllByPublisher(Publisher publisher);

    List<Product> findAllByLanguage(Language language);

    List<Product> findAllByPriceGreaterThanAndPriceLessThan(double greater, double less);

    @Query(value = "SELECT * FROM products " +
            " WHERE MATCH (name, description, publishingYear) AGAINST (?1 IN NATURAL LANGUAGE MODE)",
            countQuery = "SELECT count(*) FROM products",
            nativeQuery = true)
    List<Product> searchProductMatch(String keyword);

    @Query(value = "SELECT p FROM products p " +
            "WHERE lower(CONCAT(p.name, ' ', p.description, ' ', p.publishingYear))  " +
            " LIKE %?1%",
            countQuery = "SELECT count(p) FROM products p")
    List<Product> searchProductRelative(String keyword);
}
