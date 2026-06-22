package com.BankSystem.ApiGatway.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;

import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Banking System API",
                version = "1.0",
                description = "Unified API Documentation for Banking Microservices. " +
                        "Includes Customer Service, Account Service, Transaction Service, " +
                        "Beneficiary Service, Authentication Service, and API Gateway."
        )
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        description = "Enter the JWT token obtained from /auth/login. " +
                "Do not include 'Bearer ' prefix."
)
public class SwaggerConfig {
}