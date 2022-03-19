package com.example.book_shopping.request;

/**
 * @author lengo
 * created on 3/19/2022
 */
public class CreateCategoryRequest {
    private String name;
    private String desc;

    public CreateCategoryRequest() {
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
