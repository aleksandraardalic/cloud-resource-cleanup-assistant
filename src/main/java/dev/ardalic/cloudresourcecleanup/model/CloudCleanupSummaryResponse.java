package dev.ardalic.cloudresourcecleanup.model;

import java.math.BigDecimal;

public record CloudCleanupSummaryResponse(
        int totalStoppedEc2Instances,
        int totalUnattachedEbsVolumes,
        BigDecimal totalEstimatedMonthlySavings
) {
}