package com.system.education.establishment.core.config;

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
        devServer.setUrl("http://127.0.0.1:9902");
        devServer.setDescription("Server URL development environment");

        var prodServer = new Server();
        prodServer.setUrl("http://10.201.183.71:9902");
        prodServer.setDescription("Server URL production environment");

        var prodServer2 = new Server();
        prodServer2.setUrl("https://establishment-0d6i.onrender.com");
        prodServer2.setDescription("Server URL production environment");

        var contact = new Contact();
        contact.setEmail("bethel@gmail.com");
        contact.setName("BETHEL");
        contact.setName("https://www.bethel.com");

        var license = new License()
                .name("Apache License")
                .url("https://bethel.com");

        var info = new Info()
                .title("ESTABLISHMENT-SERVICE RESTFUL API")
                .version("1.0.0")
                .contact(contact)
                .description("ESTABLISHMENT MICROSERVICE RESTFUL API")
                .termsOfService("https://www.bethel.com")
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
