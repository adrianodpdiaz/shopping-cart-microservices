package com.adpd.notification.config;

import com.adpd.amqp.constants.ExchangeQueueConstants;
import lombok.Getter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class NotificationRabbitMQConfig {

    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(ExchangeQueueConstants.INTERNAL_EXCHANGE);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue(ExchangeQueueConstants.NOTIFICATION_QUEUE);
    }

    @Bean
    public Binding internalToNotificationBinding() {
        return BindingBuilder
                .bind(notificationQueue())
                .to(internalTopicExchange())
                .with(ExchangeQueueConstants.INTERNAL_NOTIFICATION_ROUTING_KEY);
    }
}
