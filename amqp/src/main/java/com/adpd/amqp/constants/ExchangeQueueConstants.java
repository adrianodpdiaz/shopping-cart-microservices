package com.adpd.amqp.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExchangeQueueConstants {

    public static final String INTERNAL_EXCHANGE = "internal.exchange";
    public static final String NOTIFICATION_QUEUE = "notification.queue";
    public static final String INTERNAL_NOTIFICATION_ROUTING_KEY = "internal.notification.routing-key";

}
