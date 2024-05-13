package com.adpd.apigateway.config;

import com.adpd.apigateway.security.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("customer", r -> r.path("/api/v1/customers/**")
                .filters(f -> f.filter(filter))
                .uri("lb://CUSTOMER"))
            .route("customer", r -> r.path("/api/v1/users/**")
                .filters(f -> f.filter(filter))
                .uri("lb://CUSTOMER"))

            .route("product", r -> r.path("/api/v1/products/**")
                .filters(f -> f.filter(filter))
                .uri("lb://PRODUCT"))

            .route("cart", r -> r.path("/api/v1/carts/**")
                .filters(f -> f.filter(filter))
                .uri("lb://CART"))

            .route("notification", r -> r.path("/api/v1/notifications/**")
                .filters(f -> f.filter(filter))
                .uri("lb://NOTIFICATION"))

            .route("auth", r -> r.path("/api/v1/auth/**")
                .filters(f -> f.filter(filter))
                .uri("lb://AUTH"))
            .build();
    }
}