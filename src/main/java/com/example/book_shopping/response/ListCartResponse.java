package com.example.book_shopping.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * @author lengo
 * created on 3/20/2022
 */
public class ListCartResponse {
    private int userId;
    private List<CartResponse> cartResponses;

    public ListCartResponse(int userId, List<CartResponse> cartResponses) {
        this.userId = userId;
        this.cartResponses = cartResponses;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<CartResponse> getCartResponses() {
        return cartResponses;
    }

    public void setCartResponses(List<CartResponse> cartResponses) {
        this.cartResponses = cartResponses;
    }
}
