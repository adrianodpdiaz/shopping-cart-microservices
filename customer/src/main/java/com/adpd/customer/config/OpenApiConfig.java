package com.adpd.customer.config;

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
                description = "OpenApi documentation for Customer service.",
                title = "OpenApi Specification - Customer",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local Environment",
                        url = "http://localhost:8080"
                )
        }
)
public class OpenApiConfig {

}