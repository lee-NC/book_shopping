package com.example.book_shopping.request;

import java.util.Set;

/**
 * @author lengo
 * created on 3/20/2022
 */
public class CreateOrderRequest {
    private Set<Integer> cartIds;
    private int addressId;

    public CreateOrderRequest() {
    }

    public Set<Integer> getCartIds() {
        return cartIds;
    }

    public int getAddressId() {
        return addressId;
    }
}
