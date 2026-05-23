package dev.ardalic.cloudresourcecleanup.model;

import java.math.BigDecimal;

public record UnattachedEbsVolumeResponse(
        String volumeId,
        String name,
        String region,
        String volumeType,
        int sizeGb,
        String state,
        String unattachedSince,
        BigDecimal estimatedMonthlySavings
) {
}