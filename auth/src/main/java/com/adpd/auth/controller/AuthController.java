package com.adpd.auth.controller;

import com.adpd.auth.resource.dto.AuthenticationDTO;
import com.adpd.auth.resource.form.AuthenticateForm;
import com.adpd.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.auth.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@Tag(name = "Customer")
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "Register new customer",
            description = "Post endpoint to register a new customer."
    )
    @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationDTO> authenticate(
            @Valid @RequestBody AuthenticateForm authenticateForm) throws AuthenticationException {
        AuthenticationDTO authenticationDTO = authService.authenticate(authenticateForm);
        log.info("authenticated user!");
        return new ResponseEntity<>(authenticationDTO, HttpStatus.OK);
    }
}
