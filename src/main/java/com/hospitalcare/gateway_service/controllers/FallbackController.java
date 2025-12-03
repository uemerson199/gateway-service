package com.hospitalcare.gateway_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/patients")
    public Mono<ResponseEntity<Map<String, String>>> patientsFallback() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "O serviço de Pacientes está indisponível.");
        response.put("error", "Service Unavailable (Circuit Breaker Open)");
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response));
    }

    @GetMapping("/scheduling")
    public Mono<ResponseEntity<Map<String, String>>> schedulingFallback() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "O serviço de Agendamento está indisponível.");
        response.put("error", "Service Unavailable (Circuit Breaker Open)");
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response));
    }
}