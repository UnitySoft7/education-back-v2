package com.school.dormitory.student.bed.press.core.config;
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
        devServer.setUrl("http://127.0.0.1:9924");
        devServer.setDescription("Server URL development environment");

        var prodServer = new Server();
        prodServer.setUrl("http://192.168.80.252:9924");
        prodServer.setDescription("Server URL production environment");

        var contact = new Contact();
        contact.setEmail("dev@gmail.com");
        contact.setName("DEV TEAM");
        contact.setName("https://www.dev_team.com");

        var license = new License()
                .name("Apache License")
                .url("https://dev_team.com");

        var info = new Info()
                .title("DormitoryStudentBedPress System APIs")
                .version("1.0.0")
                .contact(contact)
                .description("DormitoryStudentBedPress System APIs")
                .termsOfService("https://www.dev_team.com")
                .license(license);

        return new OpenAPI().info(info)
                .servers(List.of(devServer, prodServer))
                .addSecurityItem(new SecurityRequirement().addList("Token"))
                .components(new Components()
                        .addSecuritySchemes("Token", new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP).scheme("Bearer").bearerFormat("JWT")
                                .description("JWT authentication with Token"))
                );

    }
}
