package com.example.book_shopping.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

/**
 * @author lengo
 * created on 3/20/2022
 */
public class OrderResponse {
    private String address;
    private int addressId;
    private String addressDesc;
    private String value;
    private String status;
    private Date createAt;
    private List<OrderProductResponse> productResponses;

    public OrderResponse() {
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getAddress() {
        return address;
    }

    public String getAddressDesc() {
        return addressDesc;
    }

    public void setAddressDesc(String addressDesc) {
        this.addressDesc = addressDesc;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderProductResponse> getProductResponses() {
        return productResponses;
    }

    public void setProductResponses(List<OrderProductResponse> productResponses) {
        this.productResponses = productResponses;
    }
}
