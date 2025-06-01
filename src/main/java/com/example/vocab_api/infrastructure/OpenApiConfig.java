package com.example.vocab_api.infrastructure;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI vocabApiOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Vocab API")
                        .description("Japanese-Chinese Vocabulary API (DDD, Spring Boot)")
                        .version("v1.0.0")
                        .license(new License().name("MIT")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project README")
                        .url("https://github.com/your-org/your-repo/wiki"));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("vocab-public")
                .pathsToMatch("/api/**")
                .build();
    }
}
