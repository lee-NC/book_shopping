package com.example.book_shopping.entity;

import javax.persistence.*;

/**
 * @author lengo
 * created on 3/18/2022
 */
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
    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean isActive;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "procedureId", referencedColumnName = "id", nullable = false)
    private Procedure procedure;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", referencedColumnName = "id", nullable = false)
    private Category category;

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
