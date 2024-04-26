package com.adpd.notification.rabbitmq;

import com.adpd.amqp.constants.ExchangeQueueConstants;
import com.adpd.notification.resource.form.SendNotificationForm;
import com.adpd.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = ExchangeQueueConstants.NOTIFICATION_QUEUE)
    public void consumer(SendNotificationForm notificationForm) {
        log.info("Consumed {} from the queue",notificationForm);
        notificationService.send(notificationForm);
    }
}
