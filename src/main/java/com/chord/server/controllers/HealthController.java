package com.chord.server.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/health")
    public Map<String, String> healthCheck() {
        return Map.of("status", "API V1 ", "message", "Artist & Song Create Endpoint Add! & Push From Github");
    }
}
