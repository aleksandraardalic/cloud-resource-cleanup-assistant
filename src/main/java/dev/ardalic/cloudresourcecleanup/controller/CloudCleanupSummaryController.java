package dev.ardalic.cloudresourcecleanup.controller;

import dev.ardalic.cloudresourcecleanup.model.CloudCleanupSummaryResponse;
import dev.ardalic.cloudresourcecleanup.service.CloudCleanupSummaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Cloud Cleanup Summary API", description = "Aggregated cloud cleanup insights")
public class CloudCleanupSummaryController {

    private final CloudCleanupSummaryService summaryService;

    public CloudCleanupSummaryController(CloudCleanupSummaryService summaryService) {
        this.summaryService = summaryService;
    }

    @Operation(
            summary = "Get cloud cleanup summary",
            description = "Returns aggregated cleanup statistics and total estimated monthly savings"
    )
    @GetMapping("/api/cloud/cleanup/summary")
    public CloudCleanupSummaryResponse getSummary() {
        return summaryService.getSummary();
    }
}