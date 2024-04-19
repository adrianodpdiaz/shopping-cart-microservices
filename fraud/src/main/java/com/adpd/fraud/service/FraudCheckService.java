package com.adpd.fraud.service;

import com.adpd.fraud.entity.FraudCheckHistory;
import com.adpd.fraud.repository.FraudCheckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FraudCheckService {

    private final FraudCheckRepository fraudCheckRepository;

    public FraudCheckHistory isFraudulentCustomer(String customerId) {
        FraudCheckHistory fraudCheckHistory = FraudCheckHistory.builder()
                .customerId(customerId)
                .isFraudster(false)
                .createdAt(LocalDateTime.now())
                .build();
        return fraudCheckRepository.save(fraudCheckHistory);
    }
}
