package com.example.book_shopping.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @author lengo
 * created on 3/20/2022
 */
public class CreateOrderRequest {
    @Size(max = 100)
    @NotNull
    private Set<@Positive Integer> cartIds;
    @NotNull
    @Positive
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
