package com.system.education.auth.auth.filter;

import com.system.education.auth.core.exception.response.DeactivateException;
import com.system.education.auth.core.exception.response.PasswordException;
import com.system.education.auth.core.exception.response.UsernameException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class JwtAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

//    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, org.springframework.security.core.AuthenticationException ex) {
        String message = "Authentication manquante, connectez-vous d'abord";

        if (ex instanceof UsernameException ||
                ex instanceof PasswordException ||
                ex instanceof DeactivateException) {
            message = ex.getMessage();
        }

        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        exchange.getResponse().getHeaders().set("Charset", "utf-8");

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> response = Map.of(
                "success", false,
                "message", message
        );

        try {
            byte[] bytes = mapper.writeValueAsBytes(response);
            return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
                    .bufferFactory()
                    .wrap(bytes)));
        } catch (JsonProcessingException e) {
            return Mono.error(e);
        }
    }
}