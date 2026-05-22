package dev.ardalic.cloudresourcecleanup.model;

public record HealthResponse(
        String status,
        String service,
        String profile,
        String version,
        String timestamp
) {
}