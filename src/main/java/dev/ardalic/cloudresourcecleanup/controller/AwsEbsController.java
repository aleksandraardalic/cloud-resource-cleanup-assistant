package dev.ardalic.cloudresourcecleanup.controller;

import dev.ardalic.cloudresourcecleanup.model.UnattachedEbsVolumeResponse;
import dev.ardalic.cloudresourcecleanup.service.AwsEbsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "AWS EBS API", description = "Endpoints for analyzing AWS EBS resources")
public class AwsEbsController {

    private final AwsEbsService awsEbsService;

    public AwsEbsController(AwsEbsService awsEbsService) {
        this.awsEbsService = awsEbsService;
    }

    @Operation(
            summary = "List unattached EBS volumes",
            description = "Returns unattached EBS volumes with optional filtering by AWS region"
    )
    @GetMapping("/api/aws/ebs/unattached")
    public List<UnattachedEbsVolumeResponse> getUnattachedVolumes(
            @RequestParam(required = false) String region
    ) {
        return awsEbsService.getUnattachedVolumes(region);
    }
}