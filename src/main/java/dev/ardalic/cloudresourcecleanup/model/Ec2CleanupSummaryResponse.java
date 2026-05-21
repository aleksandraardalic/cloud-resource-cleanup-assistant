package dev.ardalic.cloudresourcecleanup.model;

import java.math.BigDecimal;
import java.util.List;

public record Ec2CleanupSummaryResponse(
        int totalStoppedInstances,
        BigDecimal totalEstimatedMonthlySavings,
        List<StoppedEc2InstanceResponse> instances
) {
}