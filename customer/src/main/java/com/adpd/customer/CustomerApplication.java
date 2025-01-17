package com.adpd.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(
    basePackages = "com.adpd.feignclients"
)
@SpringBootApplication(
    scanBasePackages = {
        "com.adpd.customer",
        "com.adpd.amqp"
    }
)
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}
