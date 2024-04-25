package com.adpd.cart.resource.form;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateCartForm {

    @NotNull(message = "customerId is required")
    @Positive(message = "customerId must be positive")
    private Long customerId;
    @Valid
    private List<CartItemForm> items;

}
