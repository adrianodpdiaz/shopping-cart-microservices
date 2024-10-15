package com.adpd.feignclients.resource.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"firstName", "lastName", "email", "taxId", "birthDate"})
public class CustomerDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String taxId;
    private LocalDate birthDate;

}
