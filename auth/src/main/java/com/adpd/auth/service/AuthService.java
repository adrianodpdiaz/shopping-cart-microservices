package com.adpd.auth.service;

import com.adpd.auth.resource.dto.AuthenticationDTO;
import com.adpd.auth.resource.form.AuthenticateForm;
import com.adpd.auth.resource.dto.UserDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.apache.http.auth.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationDTO authenticate(AuthenticateForm authenticateForm) throws AuthenticationException {
        UserDetailsDTO userDetailsDTO = userService.getUser(authenticateForm.getEmail());
        authenticateForm.setPassword(passwordEncoder.encode(authenticateForm.getPassword()));

        if(userDetailsDTO == null || !userDetailsDTO.getPassword().equals(authenticateForm.getPassword())) {
            throw new AuthenticationException();
        }

        String accessToken = jwtService.generate(userDetailsDTO, "ACCESS");
        String refreshToken = jwtService.generate(userDetailsDTO, "REFRESH");
        LocalDateTime tokenExpiration = jwtService.getTokenExpiration(accessToken)
            .toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();

        return new AuthenticationDTO(accessToken, refreshToken, "Bearer", tokenExpiration);
    }
}
