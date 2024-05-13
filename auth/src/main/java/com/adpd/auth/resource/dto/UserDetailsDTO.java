package com.adpd.auth.resource.dto;

import com.adpd.feignclients.resource.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"email", "password"})
public class UserDetailsDTO {

    private String email;
    private String password;
    private Role role;

}
