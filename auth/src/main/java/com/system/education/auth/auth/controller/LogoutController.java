package com.system.education.auth.auth.controller;

import com.system.education.auth.auth.command.LogoutCommand;
import com.system.education.auth.auth.logout.JwtBlacklistService;
import com.system.education.auth.core.dto.MessageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/auth/")
@Tag(name = "auth", description = "Auth sign in REST API")
public class LogoutController {
    private final JwtBlacklistService jwtBlacklistService;

    @PostMapping("logout")
    public Mono<ResponseEntity<MessageResponse>> logout(@Valid @RequestBody LogoutCommand command) {
        if (command.token() == null) {
            return Mono.just(ResponseEntity.ok(new MessageResponse(false, "Pas de token fourni")));
        }
        String token = command.token().substring(7);
        return jwtBlacklistService.blacklistToken(token)
                .flatMap(blacklisted -> Mono.just(ResponseEntity.ok(new MessageResponse(false, "Logout successful"))));
    }
}
