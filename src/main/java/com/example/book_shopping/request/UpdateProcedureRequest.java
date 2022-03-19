package com.example.book_shopping.request;

/**
 * @author lengo
 * created on 3/19/2022
 */
public class UpdateProcedureRequest {
    private String name;
    private String country;

    public UpdateProcedureRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
