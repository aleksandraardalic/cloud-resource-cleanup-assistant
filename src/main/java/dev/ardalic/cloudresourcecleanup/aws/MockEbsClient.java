package dev.ardalic.cloudresourcecleanup.aws;

import dev.ardalic.cloudresourcecleanup.model.UnattachedEbsVolumeResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Profile("dev")
public class MockEbsClient implements EbsClientPort {

    @Override
    public List<UnattachedEbsVolumeResponse> getUnattachedVolumes() {
        return List.of(
                new UnattachedEbsVolumeResponse(
                        "vol-0a1b2c3d4e5f67890",
                        "old-database-volume",
                        "eu-central-1",
                        "gp3",
                        100,
                        "available",
                        "2026-05-01",
                        BigDecimal.valueOf(8.00)
                ),
                new UnattachedEbsVolumeResponse(
                        "vol-0123456789abcdef0",
                        "unused-backup-volume",
                        "eu-west-1",
                        "gp2",
                        250,
                        "available",
                        "2026-04-18",
                        BigDecimal.valueOf(25.00)
                )
        );
    }
}