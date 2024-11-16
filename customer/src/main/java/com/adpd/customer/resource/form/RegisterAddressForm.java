package com.adpd.customer.resource.form;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterAddressForm {

    @NotEmpty(message = "street address is required")
    private String streetAddress;
    @NotEmpty(message = "city is required")
    private String city;
    private String state;
    @NotNull(message = "country is required")
    private String country;
    @NotEmpty(message = "zip-code is required")
    private String zipCode;

}
