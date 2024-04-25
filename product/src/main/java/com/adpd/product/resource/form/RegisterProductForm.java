package com.adpd.product.resource.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RegisterProductForm {

    @NotEmpty(message = "product name is required")
    private String name;
    @NotEmpty(message = "description is required")
    private String description;
    @NotEmpty(message = "category is required")
    private String category;
    @NotNull(message = "price is required")
    private BigDecimal price;

}
