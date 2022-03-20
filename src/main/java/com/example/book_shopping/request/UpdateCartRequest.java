package com.example.book_shopping.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author lengo
 * created on 3/20/2022
 */
public class UpdateCartRequest {
    @Positive
    @NotNull
    private int amount;

    public UpdateCartRequest() {
    }

    public int getAmount() {
        return amount;
    }
}
