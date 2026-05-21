package dev.ardalic.cloudresourcecleanup.model;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error
) {
}