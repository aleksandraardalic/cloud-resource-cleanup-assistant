package dev.ardalic.cloudresourcecleanup.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI cloudCleanupOpenApi() {

        return new OpenAPI()
                .info(new Info()
                        .title("Cloud Resource Cleanup Assistant API")
                        .description("""
                                Backend service for identifying unused cloud resources
                                and estimating potential cloud cost savings.
                                """)
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Aleksandra Ardalic")
                                .url("https://github.com/aleksandraardalic")
                        )
                )
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub Repository")
                        .url("https://github.com/aleksandraardalic/cloud-resource-cleanup-assistant")
                );
    }
}