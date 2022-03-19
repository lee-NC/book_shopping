package com.example.book_shopping.response;

import com.example.book_shopping.entity.Category;
import com.example.book_shopping.entity.Language;
import com.example.book_shopping.entity.Procedure;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lengo
 * created on 3/18/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private int id;
    private String name;
    private String desc;
    private int publishingYear;
    private Language language;
    private String price;
    private int amount;
    private boolean isActive;
    private Procedure procedure;
    private Category category;
}
