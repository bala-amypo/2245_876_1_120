package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        SecurityScheme bearerAuth = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        return new OpenAPI()
                .components(
                        new Components()
                                .addSecuritySchemes("bearerAuth", bearerAuth)
                )
                .addSecurityItem(
                        new SecurityRequirement().addList("bearerAuth")
                )
                .servers(List.of(
                        // 🔥 THIS MUST MATCH YOUR ACTUAL SERVER
                        new Server().url("http://localhost:9001")
                ));
    }
}
