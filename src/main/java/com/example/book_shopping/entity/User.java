package com.example.book_shopping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author lengo
 * created on 3/18/2022
 */
@Entity(name = "users")
public class User extends BaseEntity {
    @Column(nullable = false)
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isAdmin;
    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean isActive;

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
