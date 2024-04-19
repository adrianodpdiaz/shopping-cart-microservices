package com.adpd.fraud.controller;

import com.adpd.fraud.entity.FraudCheckHistory;
import com.adpd.fraud.resource.FraudCheckHistoryDTO;
import com.adpd.fraud.service.FraudCheckService;
import jakarta.validation.constraints.Size;
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
    public ResponseEntity<FraudCheckHistoryDTO> isFraudster(
            @PathVariable("customerId") @Size(min = 6, max = 8, message = "customerId must have 6 to 8 characters")
            String customerId) {
        FraudCheckHistory entity = fraudCheckService.isFraudulentCustomer(customerId);
        FraudCheckHistoryDTO dto = FraudCheckHistoryDTO.builder()
                .customerId(customerId)
                .isFraudster(entity.getIsFraudster())
                .createdAt(entity.getCreatedAt())
                .build();

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
