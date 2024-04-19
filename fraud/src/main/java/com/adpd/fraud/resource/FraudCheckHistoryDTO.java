package com.adpd.fraud.resource;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FraudCheckHistoryDTO {

    @NotEmpty
    private String customerId;
    @NotEmpty
    private Boolean isFraudster;
    @NotEmpty
    private LocalDateTime createdAt;

}
