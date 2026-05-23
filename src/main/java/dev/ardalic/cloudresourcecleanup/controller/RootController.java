package dev.ardalic.cloudresourcecleanup.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RootController {

    @GetMapping("/")
    public Map<String, String> root() {
        return Map.of(
                "service", "cloud-resource-cleanup-assistant",
                "status", "UP",
                "swagger", "/swagger-ui/index.html",
                "health", "/api/health"
        );
    }
}