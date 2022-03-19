package com.example.book_shopping.response;

import java.util.List;

/**
 * @author lengo
 * created on 3/20/2022
 */
public class ListCartResponse {
    private UserCartResponse userCartResponse;
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
