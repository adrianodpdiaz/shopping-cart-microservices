package com.adpd.auth.controller;

import com.adpd.auth.resource.form.CreateUserForm;
import com.adpd.auth.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@Tag(name = "User")
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Operation(
        summary = "Register new user",
        description = "Post endpoint to register a new user."
    )
    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@Valid @RequestBody CreateUserForm createUserForm) {
        Long userId = userService.createUser(createUserForm);
        log.info("registered user {}", userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
