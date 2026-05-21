package dev.ardalic.cloudresourcecleanup.controller;

import dev.ardalic.cloudresourcecleanup.model.Ec2CleanupSummaryResponse;
import dev.ardalic.cloudresourcecleanup.model.StoppedEc2InstanceResponse;
import dev.ardalic.cloudresourcecleanup.service.AwsEc2Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "AWS EC2 API", description = "Endpoints for analyzing AWS EC2 resources")
public class AwsEc2Controller {

    private final AwsEc2Service awsEc2Service;

    public AwsEc2Controller(AwsEc2Service awsEc2Service) {
        this.awsEc2Service = awsEc2Service;
    }

    @Operation(
            summary = "List stopped EC2 instances",
            description = "Returns stopped EC2 instances with estimated monthly savings"
    )
    @GetMapping("/api/aws/ec2/stopped")
    public List<StoppedEc2InstanceResponse> getStoppedInstances() {
        return awsEc2Service.getStoppedInstances();
    }

    @Operation(
            summary = "Get EC2 cleanup summary",
            description = "Returns stopped EC2 instances together with total estimated monthly savings"
    )
    @GetMapping("/api/aws/ec2/stopped/summary")
    public Ec2CleanupSummaryResponse getStoppedInstancesSummary() {
        return awsEc2Service.getStoppedInstancesSummary();
    }
}