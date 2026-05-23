package dev.ardalic.cloudresourcecleanup.service;

import dev.ardalic.cloudresourcecleanup.aws.EbsClientPort;
import dev.ardalic.cloudresourcecleanup.exception.InvalidAwsRegionException;
import dev.ardalic.cloudresourcecleanup.model.UnattachedEbsVolumeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AwsEbsService {

    private static final Logger logger =
            LoggerFactory.getLogger(AwsEbsService.class);

    private final EbsClientPort ebsClientPort;
    private final List<String> supportedRegions;

    public AwsEbsService(
            EbsClientPort ebsClientPort,
            @Value("${aws.supported-regions}") List<String> supportedRegions
    ) {
        this.ebsClientPort = ebsClientPort;
        this.supportedRegions = supportedRegions;
    }

    public List<UnattachedEbsVolumeResponse> getUnattachedVolumes(String region) {
        logger.info("Fetching unattached EBS volumes");

        List<UnattachedEbsVolumeResponse> volumes =
                ebsClientPort.getUnattachedVolumes();

        if (region == null || region.isBlank()) {
            logger.info("Returning all unattached EBS volumes");
            return volumes;
        }

        logger.info("Filtering unattached EBS volumes by region: {}", region);

        if (!supportedRegions.contains(region.toLowerCase())) {
            logger.warn("Invalid AWS region requested: {}", region);
            throw new InvalidAwsRegionException(region);
        }

        return volumes.stream()
                .filter(volume -> volume.region().equalsIgnoreCase(region))
                .toList();
    }
}