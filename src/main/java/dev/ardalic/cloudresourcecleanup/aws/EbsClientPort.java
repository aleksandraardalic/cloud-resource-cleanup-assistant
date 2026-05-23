package dev.ardalic.cloudresourcecleanup.aws;

import dev.ardalic.cloudresourcecleanup.model.UnattachedEbsVolumeResponse;

import java.util.List;

public interface EbsClientPort {

    List<UnattachedEbsVolumeResponse> getUnattachedVolumes();
}