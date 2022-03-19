package com.example.book_shopping.entity;

import javax.persistence.*;

/**
 * @author lengo
 * created on 3/18/2022
 */
@Entity(name = "orders")
public class Order extends BaseEntity {
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cartId", referencedColumnName = "id", nullable = false)
    private Cart cart;
    @Column(nullable = false)
    private double value;
    @Column(nullable = false)
    private String status;

    public Order() {
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
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
}
