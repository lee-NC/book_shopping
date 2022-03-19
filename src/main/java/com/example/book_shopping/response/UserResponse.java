package com.example.book_shopping.response;

/**
 * @author lengo
 * created on 3/18/2022
 */
public class UserResponse {
    private int id;
    private String fullName;
    private boolean isActive;
    private boolean isAdmin;

    public UserResponse() {
    }

    public UserResponse(int id, String fullName, boolean isActive, boolean isAdmin) {
        this.id = id;
        this.fullName = fullName;
        this.isActive = isActive;
        this.isAdmin = isAdmin;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
