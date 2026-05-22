package dev.ardalic.cloudresourcecleanup.service;

import dev.ardalic.cloudresourcecleanup.model.HealthResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class HealthService {

    public HealthResponse getHealthStatus(String activeProfile) {

        return new HealthResponse(
                "UP",
                "cloud-resource-cleanup-assistant",
                activeProfile,
                "0.0.1",
                LocalDateTime.now().toString()
        );
    }
}