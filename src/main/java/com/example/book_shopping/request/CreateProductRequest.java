package com.example.book_shopping.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author lengo
 * created on 3/19/2022
 */
@Getter
@NoArgsConstructor
public class CreateProductRequest {
    private String name;
    private String description;
    private int publishingYear;
    private int languageId;
    private double price;
    private int amount;
    private int procedureId;
    private int categoryId;
}
