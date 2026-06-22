package com.BankSystem.ApiGatway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(
            ServerHttpSecurity http) {

        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        SecurityWebFiltersOrder.AUTHENTICATION)

                .authorizeExchange(exchange -> exchange

                        // Public APIs - No authentication required
                        .pathMatchers(
                                "/auth/register",
                                "/auth/login"
                        ).permitAll()

                        // Health check endpoints
                        .pathMatchers(
                                "/actuator/**",
                                "/actuator/health"
                        ).permitAll()

                        // Swagger/OpenAPI Documentation - Public access
                        .pathMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/webjars/**"
                        ).permitAll()

                        // Customer Service
                        .pathMatchers("/customers/**")
                        .hasAnyRole(
                                "CUSTOMER",
                                "BANK_EMPLOYEE",
                                "ADMIN"
                        )

                        // Account Service
                        .pathMatchers("/accounts/**")
                        .hasAnyRole(
                                "CUSTOMER",
                                "BANK_EMPLOYEE",
                                "ADMIN"
                        )

                        // Transaction Service
                        .pathMatchers("/transactions/**")
                        .hasAnyRole(
                                "CUSTOMER",
                                "ADMIN"
                        )

                        // Beneficiary Service
                        .pathMatchers("/beneficiaries/**")
                        .hasAnyRole(
                                "CUSTOMER",
                                "ADMIN"
                        )

                        // Loan Service - View loans
                        .pathMatchers("/loans/**")
                        .hasAnyRole(
                                "CUSTOMER",
                                "BANK_EMPLOYEE",
                                "ADMIN"
                        )

                        // Loan Approval/Rejection - Bank Employee and Admin only
                        .pathMatchers(
                                "/loans/*/approve",
                                "/loans/*/reject"
                        )
                        .hasAnyRole(
                                "BANK_EMPLOYEE",
                                "ADMIN"
                        )

                        // Notification Service
                        .pathMatchers("/notifications/**")
                        .hasAnyRole(
                                "CUSTOMER",
                                "BANK_EMPLOYEE",
                                "ADMIN"
                        )

                        // Admin APIs - Admin only
                        .pathMatchers("/admin/**")
                        .hasRole("ADMIN")

                        // All other requests require authentication
                        .anyExchange().authenticated()
                )
                .build();
    }
}
