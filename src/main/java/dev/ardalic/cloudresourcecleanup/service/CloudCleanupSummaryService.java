package dev.ardalic.cloudresourcecleanup.service;

import dev.ardalic.cloudresourcecleanup.model.CloudCleanupSummaryResponse;
import dev.ardalic.cloudresourcecleanup.model.StoppedEc2InstanceResponse;
import dev.ardalic.cloudresourcecleanup.model.UnattachedEbsVolumeResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CloudCleanupSummaryService {

    private final AwsEc2Service awsEc2Service;
    private final AwsEbsService awsEbsService;

    public CloudCleanupSummaryService(
            AwsEc2Service awsEc2Service,
            AwsEbsService awsEbsService
    ) {
        this.awsEc2Service = awsEc2Service;
        this.awsEbsService = awsEbsService;
    }

    public CloudCleanupSummaryResponse getSummary() {
        var ec2Instances = awsEc2Service.getStoppedInstances(null);
        var ebsVolumes = awsEbsService.getUnattachedVolumes(null);

        BigDecimal ec2Savings = ec2Instances.stream()
                .map(StoppedEc2InstanceResponse::estimatedMonthlySavings)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal ebsSavings = ebsVolumes.stream()
                .map(UnattachedEbsVolumeResponse::estimatedMonthlySavings)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CloudCleanupSummaryResponse(
                ec2Instances.size(),
                ebsVolumes.size(),
                ec2Savings.add(ebsSavings)
        );
    }
}