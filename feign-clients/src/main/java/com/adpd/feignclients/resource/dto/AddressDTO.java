package com.adpd.feignclients.resource.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "streetAddress", "city", "state", "country", "zipCode"})
public class AddressDTO {

    private Long id;
    private String streetAddress;
    private String city;
    private String state;
    private String country;
    private String zipCode;

}
