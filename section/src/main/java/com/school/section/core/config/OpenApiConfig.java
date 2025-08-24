package com.school.section.core.config;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        var devServer = new Server();
        devServer.setUrl("http://127.0.0.1:9903");
        devServer.setDescription("Server URL development environment");

        var prodServer = new Server();
        prodServer.setUrl("http://10.201.183.71:9903");
        prodServer.setDescription("Server URL production environment");

        var prodServer2 = new Server();
        prodServer2.setUrl("https://section-aw2d.onrender.com");
        prodServer2.setDescription("Server URL production environment");

        var contact = new Contact();
        contact.setEmail("dev@gmail.com");
        contact.setName("DEV TEAM");
        contact.setName("https://www.dev_team.com");

        var license = new License()
                .name("Apache License")
                .url("https://dev_team.com");

        var info = new Info()
                .title("Section System APIs")
                .version("1.0.0")
                .contact(contact)
                .description("Section System APIs")
                .termsOfService("https://www.dev_team.com")
                .license(license);

        return new OpenAPI().info(info)
                .servers(List.of(devServer, prodServer, prodServer2))
                .addSecurityItem(new SecurityRequirement().addList("Token"))
                .components(new Components()
                        .addSecuritySchemes("Token", new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP).scheme("Bearer").bearerFormat("JWT")
                                .description("JWT authentication with Token"))
                );

    }
}
