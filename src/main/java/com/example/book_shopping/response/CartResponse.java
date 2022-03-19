package com.example.book_shopping.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author lengo
 * created on 3/20/2022
 */
public class CartResponse {
    private int id;
    private int amount;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ProductCartResponse productCartResponse;

    public CartResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ProductCartResponse getProductCartResponse() {
        return productCartResponse;
    }

    public void setProductCartResponse(ProductCartResponse productCartResponse) {
        this.productCartResponse = productCartResponse;
    }
}
