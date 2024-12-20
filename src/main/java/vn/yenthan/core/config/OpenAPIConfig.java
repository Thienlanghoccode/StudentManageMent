package vn.yenthan.core.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI openAPI(@Value("${open.api.title}") String title,
                           @Value("${open.api.version}") String version,
                           @Value("${open.api.description}") String description,
                           @Value("${open.api.serverUrl}")String Url,
                           @Value("${open.api.serverName}") String serverName) {
        return new OpenAPI().info(new Info().title(title)
                        .contact(new Contact()
                                .name("yenthan2004")
                                .email("yenthan2004@gmail.com")
                        )
                        .version(version)
                        .description(description)
                        .license(new License().name("API License 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .servers(List.of(new Server().url(Url).description(serverName)))
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        "bearerAuth",
                                        new SecurityScheme()
                                                .description("JWT main description")
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                                .in(SecurityScheme.In.HEADER)))
                .security(List.of(new SecurityRequirement().addList("bearerAuth")));
    }

    @Bean
    public GroupedOpenApi studentApi() {
        return GroupedOpenApi.builder()
                .group("api-v1-student")
                .pathsToMatch("/api/v1/student/**")
                .packagesToScan("vn.yenthan")
                .build();
    }

    @Bean
    public GroupedOpenApi classroomApi() {
        return GroupedOpenApi.builder()
                .group("api-v1-classroom")
                .pathsToMatch("/api/v1/classroom/**")
                .packagesToScan("vn.yenthan")
                .build();
    }

    @Bean
    public GroupedOpenApi gradeApi() {
        return GroupedOpenApi.builder()
                .group("api-v1-grade")
                .pathsToMatch("/api/v1/grades/**")
                .packagesToScan("vn.yenthan")
                .build();
    }

}
