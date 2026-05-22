package dev.ardalic.cloudresourcecleanup.service;

import dev.ardalic.cloudresourcecleanup.aws.Ec2ClientPort;
import dev.ardalic.cloudresourcecleanup.exception.InvalidAwsRegionException;
import dev.ardalic.cloudresourcecleanup.model.Ec2CleanupSummaryResponse;
import dev.ardalic.cloudresourcecleanup.model.StoppedEc2InstanceResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AwsEc2Service {

    private static final Logger logger =
            LoggerFactory.getLogger(AwsEc2Service.class);

    private final Ec2ClientPort ec2ClientPort;

    @Value("${aws.supported-regions}")
    private List<String> supportedRegions;

    public AwsEc2Service(Ec2ClientPort ec2ClientPort) {
        this.ec2ClientPort = ec2ClientPort;
    }


    public List<StoppedEc2InstanceResponse> getStoppedInstances(String region) {
        logger.info("Fetching stopped EC2 instances");
        List<StoppedEc2InstanceResponse> instances = ec2ClientPort.getStoppedInstances();

        if (region == null || region.isBlank()) {
            logger.info("Returning all stopped instances");
            return instances;
        }

        logger.info("Filtering instances by region: {}", region);

        if (!supportedRegions.contains(region.toLowerCase())) {
            logger.warn("Invalid AWS region requested: {}", region);
            throw new InvalidAwsRegionException(region);
        }

        return instances.stream()
                .filter(instance -> instance.region().equalsIgnoreCase(region))
                .toList();
    }

    public Ec2CleanupSummaryResponse getStoppedInstancesSummary() {
        List<StoppedEc2InstanceResponse> instances = getStoppedInstances(null);

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

