package com.system.education.auth.role.cmd.api.controller;

import com.system.education.auth.core.dto.MessageResponse;
import com.system.education.auth.role.cmd.api.command.RoleGlobalCreatedRequest;
import com.system.education.auth.role.core.payload.RolePayload;
import com.system.education.auth.role.core.util.RoleUtilsConstants;
import com.system.education.auth.role.query.api.handler.RoleEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/role/create-global-role")
@Tag(name = "role", description = "Data REST API for role resource")
public class CreateRoleGlobalController {
    private final RoleEventHandler roleEventHandler;
    private final RolePayload rolePayload;

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Create global role")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> create(@Valid @RequestBody RoleGlobalCreatedRequest request) {
        return rolePayload.exception(request)
                .flatMap(messageResponse -> {
                    if (!messageResponse.success()) {
                        return Mono.just(new ResponseEntity<>(new MessageResponse(false,
                                messageResponse.message()), HttpStatus.BAD_REQUEST));
                    }
                    return roleEventHandler.createGlobal(request)
                            .flatMap(role -> {
                                if (role != null) {
                                    return Mono.just(new ResponseEntity<>(new MessageResponse(true,
                                            RoleUtilsConstants.create), HttpStatus.CREATED));
                                } else {
                                    return Mono.just(new ResponseEntity<>(new MessageResponse(false,
                                            "Échec de la création du rôle"), HttpStatus.INTERNAL_SERVER_ERROR));
                                }
                            });
                });
    }
}
