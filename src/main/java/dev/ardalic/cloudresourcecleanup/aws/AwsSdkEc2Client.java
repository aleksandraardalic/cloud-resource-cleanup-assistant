package dev.ardalic.cloudresourcecleanup.aws;

import dev.ardalic.cloudresourcecleanup.model.StoppedEc2InstanceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("prod")
public class AwsSdkEc2Client implements Ec2ClientPort {

    private static final Logger logger =
            LoggerFactory.getLogger(AwsSdkEc2Client.class);

    @Override
    public List<StoppedEc2InstanceResponse> getStoppedInstances() {

        logger.info("Fetching EC2 instances using AWS SDK");

        // TODO:
        // Real AWS SDK integration will be implemented here

        return List.of();
    }
}