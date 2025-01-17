package com.adpd.customer.resource.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCustomerForm {

    @NotEmpty(message = "first name is required")
    private String firstName;
    @NotEmpty(message = "last name is required")
    private String lastName;
    @NotEmpty(message = "e-mail is required")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,8}", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;
    @Past
    @JsonFormat(pattern="dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "birth date is required")
    private LocalDate birthDate;
    @NotEmpty
    @Pattern(regexp = "\\d{11}", message = "taxId must contain 11 digits")
    private String taxId;

}
