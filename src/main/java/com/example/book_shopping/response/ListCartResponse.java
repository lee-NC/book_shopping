package com.example.book_shopping.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * @author lengo
 * created on 3/20/2022
 */
public class ListCartResponse {
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private UserCartResponse userCartResponse;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<CartResponse> cartResponses;

    public ListCartResponse(UserCartResponse userCartResponse, List<CartResponse> cartResponses) {
        this.userCartResponse = userCartResponse;
        this.cartResponses = cartResponses;
    }

    public UserCartResponse getUserCartResponse() {
        return userCartResponse;
    }

    public void setUserCartResponse(UserCartResponse userCartResponse) {
        this.userCartResponse = userCartResponse;
    }

    public List<CartResponse> getCartResponses() {
        return cartResponses;
    }

    public void setCartResponses(List<CartResponse> cartResponses) {
        this.cartResponses = cartResponses;
    }
}
