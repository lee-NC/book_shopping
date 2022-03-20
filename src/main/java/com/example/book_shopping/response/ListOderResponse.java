package com.example.book_shopping.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * @author lengo
 * created on 3/20/2022
 */
public class ListOderResponse {
    private int userId;
    private List<OrderResponse> orderResponses;

    public ListOderResponse(int userId, List<OrderResponse> orderResponses) {
        this.userId = userId;
        this.orderResponses = orderResponses;
    }

    public List<OrderResponse> getOrderResponses() {
        return orderResponses;
    }

    public void setOrderResponses(List<OrderResponse> orderResponses) {
        this.orderResponses = orderResponses;
    }
}
