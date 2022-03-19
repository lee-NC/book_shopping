package com.example.book_shopping.request;

/**
 * @author lengo
 * created on 3/19/2022
 */
public class CreateProductRequest {
    private String name;
    private String description;
    private int publishingYear;
    private int languageId;
    private double price;
    private int amount;
    private int procedureId;
    private int categoryId;

    public CreateProductRequest() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public int getLanguageId() {
        return languageId;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public int getProcedureId() {
        return procedureId;
    }

    public int getCategoryId() {
        return categoryId;
    }
}
