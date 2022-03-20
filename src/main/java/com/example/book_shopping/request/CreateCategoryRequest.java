package com.example.book_shopping.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author lengo
 * created on 3/19/2022
 */
public class CreateCategoryRequest {
    @NotNull
    @Size(max = 250)
    @Pattern(regexp = "^[A-Za-z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ0-9\\s-,.@$!:{%*?}/&]$")
    private String name;
    @NotNull
    @Size(max = 250)
    @Pattern(regexp = "^[A-Za-z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ0-9\\s-,.@$!:{%*?}/&]$")
    private String desc;

    public CreateCategoryRequest() {
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
