package com.example.book_shopping.response;

/**
 * @author lengo
 * created on 3/20/2022
 */
public class UserCartResponse {
    private int id;
    private String fullName;

    public UserCartResponse(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
