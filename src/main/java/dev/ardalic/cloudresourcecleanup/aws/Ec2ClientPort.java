package dev.ardalic.cloudresourcecleanup.aws;

import dev.ardalic.cloudresourcecleanup.model.StoppedEc2InstanceResponse;

import java.util.List;

public interface Ec2ClientPort {

    List<StoppedEc2InstanceResponse> getStoppedInstances();
}