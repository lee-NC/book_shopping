package com.example.book_shopping.response;

import com.example.book_shopping.entity.Category;
import com.example.book_shopping.entity.Language;
import com.example.book_shopping.entity.Publisher;

import java.util.List;

/**
 * @author lengo
 * created on 3/18/2022
 */
public class ProductResponse {
    private int id;
    private String name;
    private String desc;
    private int publishingYear;
    private Language language;
    private double price;
    private int amount;
    private boolean isActive;
    private Publisher publisher;
    private Category category;
    private List<String> imageName;

    public ProductResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<String> getImageName() {
        return imageName;
    }

    public void setImageName(List<String> imageName) {
        this.imageName = imageName;
    }
}
