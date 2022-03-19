package com.example.book_shopping.request;

/**
 * @author lengo
 * created on 3/19/2022
 */
public class AddressRequest {
    private String addressDetail;
    private String description;

    public AddressRequest() {
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
