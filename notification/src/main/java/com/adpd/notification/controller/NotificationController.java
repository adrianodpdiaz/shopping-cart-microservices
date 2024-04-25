package com.adpd.notification.controller;

import com.adpd.notification.resource.form.SendNotificationForm;
import com.adpd.notification.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@Tag(name = "Notification")
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Operation(
            summary = "Send notification",
            description = "Post endpoint to a notification."
    )
    @Transactional
    @PostMapping
    public void send(@Valid @RequestBody SendNotificationForm sendNotificationForm) {
        notificationService.send(sendNotificationForm);
        log.info("notification sent {}", sendNotificationForm);
    }
}
