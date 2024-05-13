package com.adpd.feignclients.client;

import com.adpd.feignclients.config.ErrorDecoderConfig;
import com.adpd.feignclients.resource.form.SendNotificationForm;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "notification", path = "/api/v1/notifications",
        configuration = ErrorDecoderConfig.class)
public interface NotificationClient {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    void send(@Valid @RequestBody SendNotificationForm sendNotificationForm);
}
