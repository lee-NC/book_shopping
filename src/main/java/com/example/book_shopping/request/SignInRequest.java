package com.example.book_shopping.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author lengo
 * created on 3/18/2022
 */
@Getter
@NoArgsConstructor
public class SignInRequest {
    private String email;
    private String password;
}
