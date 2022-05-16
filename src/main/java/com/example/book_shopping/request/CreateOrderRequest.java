package com.example.book_shopping.request;

import com.example.book_shopping.entity.Product;

import java.util.List;
import java.util.Set;

/**
 * @author lengo
 * created on 3/20/2022
 */
public class CreateOrderRequest {

    private Set<CreateProductOrderRequest> cartIds;

    // private int addressId;

    public CreateOrderRequest() {
    }

    public Set<CreateProductOrderRequest> getCartIds() {
        return cartIds;
    }

    // public int getAddressId() {
    //     return addressId;
    // }


    private String address;
    private String email;
    private String name;
    private String phoneNumber;

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    
}
