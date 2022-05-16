package com.example.book_shopping.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

/**
 * @author lengo
 * created on 3/18/2022
 */
@Entity(name = "orders")
public class Order extends BaseEntity {

    @JsonBackReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
    private Set<OrderProduct> orderProducts;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne()
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @JsonBackReference
    private Address address;

    private String addressDesc;
    private String email;
    private String name;
    private String phoneNumber;
    @Column(nullable = false)
    private double value;
    @Column(nullable = false)
    private String status;

    public Order() {
    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddressDesc() {
        return addressDesc;
    }
    public void setAddressDesc(String addressDesc) {
        this.addressDesc = addressDesc;
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
