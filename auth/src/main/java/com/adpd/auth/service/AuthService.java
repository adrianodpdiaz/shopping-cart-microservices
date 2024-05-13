package com.adpd.auth.service;

import com.adpd.auth.resource.dto.AuthenticationDTO;
import com.adpd.auth.resource.form.AuthenticateForm;
import com.adpd.feignclients.customer.client.CustomerClient;
import com.adpd.feignclients.customer.resource.dto.UserDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.apache.http.auth.AuthenticationException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomerClient customerClient;
    private final JwtService jwtService;

    public AuthenticationDTO authenticate(AuthenticateForm authenticateForm) throws AuthenticationException {
        UserDetailsDTO userDetailsDTO = customerClient.getUser(authenticateForm.getEmail()).getBody();

        if(userDetailsDTO == null || !userDetailsDTO.getPassword().equals(authenticateForm.getPassword())) {
            throw new AuthenticationException();
        }

        String accessToken = jwtService.generate(userDetailsDTO, "ACCESS");
        String refreshToken = jwtService.generate(userDetailsDTO, "REFRESH");

        return new AuthenticationDTO(accessToken, refreshToken);
    }
}
