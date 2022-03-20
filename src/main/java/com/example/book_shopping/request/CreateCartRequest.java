package com.example.book_shopping.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author lengo
 * created on 3/20/2022
 */
public class CreateCartRequest {
    @Positive
    @NotNull
    private int productId;
    @Positive
    @NotNull
    private int amount;

    public CreateCartRequest() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
