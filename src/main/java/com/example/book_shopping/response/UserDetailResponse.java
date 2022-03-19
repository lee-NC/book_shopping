package com.example.book_shopping.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lengo
 * created on 3/18/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private boolean isActive;
    private boolean isAdmin;
}
