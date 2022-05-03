package com.example.book_shopping.request;

import javax.validation.constraints.*;

/**
 * @author lengo
 * created on 3/19/2022
 */
public class CreateProductRequest {
    @NotNull
    @Pattern(regexp = "^[A-Za-z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ0-9\\s-,.@$!:{%*?}/&].{0,250}.|$")
    private String name;
    @Pattern(regexp = "^[A-Za-z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ0-9\\s-,.@$!:{%*?}/&].{0,250}.|$")
    private String description;
    @Positive
    @Max(value = 2023)
    private int publishingYear;
    @NotNull
    @Positive
    private int languageId;
    @Positive
    @NotNull
    private double price;
    @Positive
    @NotNull
    private int amount;
    @Positive
    @NotNull
    private int publisherId;
    @Positive
    @NotNull
    private int categoryId;

    public CreateProductRequest() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public int getLanguageId() {
        return languageId;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public int getCategoryId() {
        return categoryId;
    }
}
