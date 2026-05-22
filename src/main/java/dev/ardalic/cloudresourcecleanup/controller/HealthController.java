package dev.ardalic.cloudresourcecleanup.controller;

import dev.ardalic.cloudresourcecleanup.model.HealthResponse;
import dev.ardalic.cloudresourcecleanup.service.HealthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;

@RestController
@Tag(name = "Health API", description = "Application health monitoring endpoints")
public class HealthController {

    private final HealthService healthService;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    public HealthController(HealthService healthService){
        this.healthService = healthService;
    }

    @Operation(
            summary = "Health check endpoint",
            description = "Returns application health status"
    )
    @GetMapping("/api/health")
    public HealthResponse health(){
       return healthService.getHealthStatus(activeProfile);
    }

}
