package com.adpd.feignclients.config;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorDecoderConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new ClientErrorDecoder();
    }
}
