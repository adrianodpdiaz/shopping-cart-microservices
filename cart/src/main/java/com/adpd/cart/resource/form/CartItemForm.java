package com.adpd.cart.resource.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CartItemForm {

    @NotNull(message = "productId is required")
    @Positive(message = "productId must be positive")
    private Long productId;
    @NotNull(message = "unit price is required")
    private BigDecimal unitPrice;
    @Positive(message = "quantity must be positive")
    private Integer quantity;

}
