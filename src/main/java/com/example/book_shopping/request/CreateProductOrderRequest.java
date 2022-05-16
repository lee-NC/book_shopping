package com.example.book_shopping.request;

import com.example.book_shopping.entity.Product;

import java.util.List;
import java.util.Set;

/**
 * @author lengo
 * created on 3/20/2022
 */
public class CreateProductOrderRequest {

    private int productId;
    private int amountProduct;

    public CreateProductOrderRequest() {
    }

    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    public int getAmountProduct() {
        return amountProduct;
    }
    public void setAmountProduct(int amountProduct) {
        this.amountProduct = amountProduct;
    }
    

    
}
