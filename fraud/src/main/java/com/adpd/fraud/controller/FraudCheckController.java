package com.adpd.fraud.controller;

import com.adpd.fraud.service.FraudCheckService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/fraud-check")
public class FraudCheckController {

    private final FraudCheckService fraudCheckService;

    @GetMapping("/{customerId}")
    public ResponseEntity<Boolean> isFraudster(
            @PathVariable("customerId") @Positive Integer customerId) {
        boolean isFraudster = fraudCheckService.isFraudulentCustomer(customerId);
        return new ResponseEntity<>(isFraudster, HttpStatus.OK);
    }
}
