package com.example.book_shopping.request;

/**
 * @author lengo
 * created on 3/20/2022
 */
public class CreateCartRequest {
    private int productId;
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
