package com.example.book_shopping.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author lengo
 * created on 3/19/2022
 */
@Getter
@NoArgsConstructor
public class AddressRequest {
    private String addressDetail;
    private String description;
}
