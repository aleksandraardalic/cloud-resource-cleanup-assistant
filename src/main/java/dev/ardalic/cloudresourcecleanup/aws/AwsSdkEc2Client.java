package dev.ardalic.cloudresourcecleanup.aws;

import dev.ardalic.cloudresourcecleanup.model.StoppedEc2InstanceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesRequest;
import software.amazon.awssdk.services.ec2.model.Filter;
import software.amazon.awssdk.services.ec2.model.Instance;
import java.math.BigDecimal;

import java.util.List;

@Component
@Profile("prod")
public class AwsSdkEc2Client implements Ec2ClientPort {

    private static final Logger logger =
            LoggerFactory.getLogger(AwsSdkEc2Client.class);

    @Value("${aws.region}")
    private String awsRegion;

    @Override
    public List<StoppedEc2InstanceResponse> getStoppedInstances() {

        logger.info("Fetching stopped EC2 instances using AWS SDK");

        Ec2Client ec2Client = Ec2Client.builder()
                .region(Region.of(awsRegion))
                .build();

        DescribeInstancesRequest request = DescribeInstancesRequest.builder()
                .filters(
                        Filter.builder()
                                .name("instance-state-name")
                                .values("stopped")
                                .build()
                )
                .build();

        return ec2Client.describeInstances(request)
                .reservations()
                .stream()
                .flatMap(reservation -> reservation.instances().stream())
                .map(this::mapToResponse)
                .toList();
    }

    private StoppedEc2InstanceResponse mapToResponse(Instance instance) {

        String name = instance.tags().stream()
                .filter(tag -> tag.key().equals("Name"))
                .map(tag -> tag.value())
                .findFirst()
                .orElse("unknown");

        return new StoppedEc2InstanceResponse(
                instance.instanceId(),
                name,
                instance.instanceTypeAsString(),
                awsRegion,
                "STOPPED",
                "unknown",
                BigDecimal.valueOf(25)
        );
    }
}