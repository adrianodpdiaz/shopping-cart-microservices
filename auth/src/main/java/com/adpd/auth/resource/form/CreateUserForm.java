package com.adpd.auth.resource.form;

import com.adpd.auth.annotation.ValidPassword;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserForm {

    @NotEmpty(message = "e-mail is required")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,8}", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;
    @ValidPassword
    private String password;

}
