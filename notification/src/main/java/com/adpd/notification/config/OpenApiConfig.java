package com.adpd.notification.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Adriano Diaz",
                        email = "adrianodpdiaz@gmail.com"
                ),
                description = "OpenApi documentation for Notification service.",
                title = "OpenApi Specification - Notification",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local Environment",
                        url = "http://localhost:8085"
                )
        }
)
public class OpenApiConfig {

}
