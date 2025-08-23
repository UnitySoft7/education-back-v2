package com.system.education.auth.auth.controller;

import com.system.education.auth.auth.command.UserDto;
import com.system.education.auth.auth.filter.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/auth/verify")
@Tag(name = "auth", description = "Auth sign in REST API")
public class TokenValidatorController {
    private final JwtTokenProvider tokenProvider;

    @Operation(summary = "Verify token")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<UserDto>> getByToken(@RequestParam String token) {
        log.info("Trying to validate token {}", token);
        return tokenProvider.validateTokenGateway(token)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}
