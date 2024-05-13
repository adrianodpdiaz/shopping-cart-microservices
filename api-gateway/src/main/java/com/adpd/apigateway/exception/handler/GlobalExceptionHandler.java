package com.adpd.apigateway.exception.handler;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.apache.http.auth.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERRORS = "errors";

    @ExceptionHandler(ExpiredJwtException.class)
    protected ResponseEntity<Object> handleExpiredJwt(ExpiredJwtException ex) {
        Map<String, String> body = new HashMap<>();

        body.put(ERRORS, ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MalformedJwtException.class)
    protected ResponseEntity<Object> handleMalformedJwt() {
        Map<String, String> body = new HashMap<>();

        body.put(ERRORS, "Invalid jwt token.");
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<Object> handleAuthenticationException() {
        Map<String, String> body = new HashMap<>();
        String authFailed = "Authentication failed.";

        body.put(ERRORS, authFailed);
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }
}
