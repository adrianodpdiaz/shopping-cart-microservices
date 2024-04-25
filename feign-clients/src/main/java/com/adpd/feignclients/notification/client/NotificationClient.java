package com.adpd.feignclients.notification.client;

import com.adpd.feignclients.config.ErrorDecoderConfig;
import com.adpd.feignclients.notification.resource.form.SendNotificationForm;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "notification", path = "/api/v1/notifications",
        configuration = ErrorDecoderConfig.class)
public interface NotificationClient {

    @PostMapping
    void send(@Valid @RequestBody SendNotificationForm sendNotificationForm);
}
