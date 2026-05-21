package dev.ardalic.cloudresourcecleanup.model;


import java.math.BigDecimal;

public record StoppedEc2InstanceResponse(
        String instanceId,
        String name,
        String region,
        String instanceType,
        String state,
        String stoppedSince,
        BigDecimal estimatedMonthlySavings
) {
}
