package com.hospitalcare.gateway_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.reactive.CorsConfigurationSource;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private CorsConfigurationSource corsConfigurationSource;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource)) // 1. Habilita o CORS
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // 2. Desabilita o CSRF (Causa comum do erro 403)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/auth/**").permitAll() // 3. Permite acesso público a todas as rotas /auth/**
                        .anyExchange().authenticated() // 4. Exige autenticação para todas as outras rotas
                )
                .build();
    }
}