package dev.ardalic.cloudresourcecleanup.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AwsEc2ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnStoppedInstances() throws Exception {
        mockMvc.perform(get("/api/v1/aws/ec2/stopped"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].instanceId").value("i-0a1b2c3d4e5f67890"));
    }

    @Test
    void shouldReturnStoppedInstancesFilteredByRegion() throws Exception {
        mockMvc.perform(get("/api/v1/aws/ec2/stopped")
                        .param("region", "eu-central-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].region").value("eu-central-1"));
    }

    @Test
    void shouldReturnBadRequestForInvalidRegion() throws Exception {
        mockMvc.perform(get("/api/v1/aws/ec2/stopped")
                        .param("region", "invalid-region"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Invalid AWS region: invalid-region"));
    }
}