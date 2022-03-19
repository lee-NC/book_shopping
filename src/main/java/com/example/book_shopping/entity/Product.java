package com.example.book_shopping.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author lengo
 * created on 3/18/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "products")
public class Product extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "publishing_year")
    private int publishingYear;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "languageId", referencedColumnName = "id", nullable = false)
    private Language language;
    @Column(nullable = false)
    private double price;
    @Column(name = "amount", nullable = false)
    private int amount;
    @Column(nullable = false, columnDefinition = "BIT(1) default true")
    private boolean isActive;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "procedureId", referencedColumnName = "id", nullable = false)
    private Procedure procedure;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", referencedColumnName = "id", nullable = false)
    private Category category;

}
