package com.adpd.cart.config;

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
                description = "OpenApi documentation for Cart service.",
                title = "OpenApi Specification - Cart",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local Environment",
                        url = "http://localhost:8082"
                )
        }
)
public class OpenApiConfig {

}