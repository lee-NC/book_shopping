package com.example.book_shopping.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author lengo
 * created on 3/18/2022
 */
@Getter
@NoArgsConstructor
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private boolean isDefault;
    private String desc;
    private String email;
    private String password;
}
