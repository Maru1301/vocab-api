package com.example.vocab_api.infrastructure;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;


@OpenAPIDefinition(
    info = @io.swagger.v3.oas.annotations.info.Info(title = "Vocab API", version = "v1"),
    security = @io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearerAuth")
)
@io.swagger.v3.oas.annotations.security.SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI vocabApiOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Vocab API")
                        .description("Japanese-Chinese Vocabulary API (DDD, Spring Boot)")
                        .version("v1.0.0")
                        .license(new License().name("MIT")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .schemaRequirement("bearerAuth", new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT"))
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
