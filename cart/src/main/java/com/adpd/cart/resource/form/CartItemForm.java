package com.adpd.cart.resource.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartItemForm {

    @NotNull(message = "productId is required")
    @Positive(message = "productId must be positive")
    private Long productId;
    @Positive(message = "quantity must be positive")
    private Integer quantity;

}
