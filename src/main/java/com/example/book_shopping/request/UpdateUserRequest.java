package com.example.book_shopping.request;

/**
 * @author lengo
 * created on 3/18/2022
 */
public class UpdateUserRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;

    public UpdateUserRequest() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
