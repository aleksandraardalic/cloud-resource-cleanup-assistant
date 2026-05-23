package dev.ardalic.cloudresourcecleanup.service;

import dev.ardalic.cloudresourcecleanup.exception.InvalidAwsRegionException;
import dev.ardalic.cloudresourcecleanup.model.Ec2CleanupSummaryResponse;
import dev.ardalic.cloudresourcecleanup.model.StoppedEc2InstanceResponse;
import dev.ardalic.cloudresourcecleanup.aws.MockEc2Client;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AwsEc2ServiceTest {

    private final AwsEc2Service awsEc2Service = new AwsEc2Service(
            new MockEc2Client(),
            List.of("eu-central-1", "eu-west-1"));

    @Test
    void shouldReturnAllStoppedInstancesWhenRegionIsNotProvided() {
        List<StoppedEc2InstanceResponse> instances = awsEc2Service.getStoppedInstances(null);

        assertEquals(2, instances.size());
    }

    @Test
    void shouldFilterStoppedInstancesByRegion() {
        List<StoppedEc2InstanceResponse> instances = awsEc2Service.getStoppedInstances("eu-central-1");

        assertEquals(1, instances.size());
        assertEquals("eu-central-1", instances.getFirst().region());
    }

    @Test
    void shouldThrowExceptionForInvalidRegion() {
        assertThrows(
                InvalidAwsRegionException.class,
                () -> awsEc2Service.getStoppedInstances("invalid-region")
        );
    }

    @Test
    void shouldReturnStoppedInstancesSummary() {
        Ec2CleanupSummaryResponse summary = awsEc2Service.getStoppedInstancesSummary();

        assertEquals(2, summary.totalStoppedInstances());
        assertEquals(0, summary.totalEstimatedMonthlySavings().compareTo(new java.math.BigDecimal("52.70")));
    }
}