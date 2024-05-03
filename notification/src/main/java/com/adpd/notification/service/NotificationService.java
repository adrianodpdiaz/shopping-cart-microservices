package com.adpd.notification.service;

import com.adpd.notification.entity.Notification;
import com.adpd.notification.mapping.NotificationMapper;
import com.adpd.notification.repository.NotificationRepository;
import com.adpd.notification.resource.form.SendNotificationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public Long send(SendNotificationForm sendNotificationForm) {
        Notification notification = notificationMapper.requestToEntity(sendNotificationForm);
        notification.setSentAt(LocalDateTime.now());
        notificationRepository.save(notification);
        return notification.getToCustomerId();
    }
}
