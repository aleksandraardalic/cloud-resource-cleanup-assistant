package dev.ardalic.cloudresourcecleanup.service;

import dev.ardalic.cloudresourcecleanup.model.Ec2CleanupSummaryResponse;
import dev.ardalic.cloudresourcecleanup.model.StoppedEc2InstanceResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AwsEc2Service {

    public List<StoppedEc2InstanceResponse> getStoppedInstances() {
        return List.of(
                new StoppedEc2InstanceResponse(
                        "i-0a1b2c3d4e5f67890",
                        "dev-test-instance",
                        "eu-central-1",
                        "t3.medium",
                        "stopped",
                        "2026-05-10",
                        BigDecimal.valueOf(34.50)
                ),
                new StoppedEc2InstanceResponse(
                        "i-0123456789abcdef0",
                        "old-staging-instance",
                        "eu-west-1",
                        "t3.small",
                        "stopped",
                        "2026-04-28",
                        BigDecimal.valueOf(18.20)
                )
        );
    }

    public Ec2CleanupSummaryResponse getStoppedInstancesSummary() {
        List<StoppedEc2InstanceResponse> instances = getStoppedInstances();

        BigDecimal totalSavings = instances.stream()
                .map(StoppedEc2InstanceResponse::estimatedMonthlySavings)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new Ec2CleanupSummaryResponse(
                instances.size(),
                totalSavings,
                instances
        );
    }
}
