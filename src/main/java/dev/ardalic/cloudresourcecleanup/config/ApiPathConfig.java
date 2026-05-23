package dev.ardalic.cloudresourcecleanup.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiPathConfig {

    @Value("${api.base-path}")
    private String apiBasePath;

    public String getApiBasePath() {
        return apiBasePath;
    }
}