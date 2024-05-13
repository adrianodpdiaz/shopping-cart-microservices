package com.adpd.apigateway.config;

import static org.springframework.http.HttpMethod.*;

import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Predicate;
import java.util.List;

@Component
public class RouterValidator {

    public static final Map<String, List<HttpMethod>> unsecuredEndpoints = Map.of(
        "/api/v1/auth/authenticate", List.of(GET, POST),
        "/api/v1/customers", List.of(POST)
    );

    public Predicate<ServerHttpRequest> isSecured = request -> {
        String requestUri = request.getURI().getPath();
        HttpMethod requestMethod = request.getMethod();
        return unsecuredEndpoints.entrySet().stream()
            .noneMatch(entry ->
                entry.getKey().equals(requestUri) &&
                    entry.getValue().contains(requestMethod));
    };
}
