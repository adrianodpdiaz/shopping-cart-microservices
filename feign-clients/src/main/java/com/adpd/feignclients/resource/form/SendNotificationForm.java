package com.adpd.feignclients.resource.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SendNotificationForm {

    @NotNull(message = "customerId is required")
    @Positive(message = "customerId must be positive")
    private Long toCustomerId;
    @NotNull(message = "customer e-mail is required")
    private String toCustomerEmail;
    @NotNull(message = "sender is required")
    private String sender;
    @NotNull(message = "message is required")
    private String message;

}
