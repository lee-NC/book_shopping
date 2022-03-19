package com.example.book_shopping.request;

/**
 * @author lengo
 * created on 3/18/2022
 */
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private boolean isDefault;
    private String addressDesc;
    private String email;
    private String password;

    public SignUpRequest() {
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

    public String getAddress() {
        return address;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public String getAddressDesc() {
        return addressDesc;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
