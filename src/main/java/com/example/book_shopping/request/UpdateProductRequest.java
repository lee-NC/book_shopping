package com.example.book_shopping.request;

import javax.validation.constraints.*;

/**
 * @author lengo
 * created on 3/19/2022
 */
public class UpdateProductRequest {
    @Size(max = 250)
    @Pattern(regexp = "^[A-Za-z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ0-9\\s-,.@$!:{%*?}/&]$")
    private String name;
    @Size(max = 2500)
    @Pattern(regexp = "^[A-Za-z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ0-9\\s-,.@$!:{%*?}/&]$")
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
    private int procedureId;
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

    public int getProcedureId() {
        return procedureId;
    }

    public int getCategoryId() {
        return categoryId;
    }
}
