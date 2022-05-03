package com.example.book_shopping.request;

import javax.validation.constraints.*;

/**
 * @author lengo
 * created on 3/19/2022
 */
public class UpdateProductRequest {
    @Pattern(regexp = "^[A-Za-z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ0-9\\s-,.@$!:{%*?}/&].{0,250}.|$")
    private String name;
    @Pattern(regexp = "^[A-Za-z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ0-9\\s-,.@$!:{%*?}/&].{0,2500}.|$")
    private String description;
    @Positive
    private int publishingYear;
    @Positive
    private int languageId;
    @Positive
    private double price;
    @Positive
    private int amount;
    @Positive
    private int publisherId;
    @Positive
    private int categoryId;

    public UpdateProductRequest() {
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
