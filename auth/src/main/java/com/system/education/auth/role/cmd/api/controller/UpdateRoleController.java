package com.system.education.auth.role.cmd.api.controller;

import com.system.education.auth.core.dto.MessageResponse;
import com.system.education.auth.role.cmd.api.command.RoleUpdatedRequest;
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
@RequestMapping(path = "/api/v1/education/role/update-role")
@Tag(name = "role", description = "Data REST API for role resource")
public class UpdateRoleController {
    private final RoleEventHandler roleEventHandler;
    private final RolePayload rolePayload;

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update role")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> update (@Valid @RequestBody RoleUpdatedRequest request) {
        return rolePayload.regexpRoleId(request.roleId())
                .flatMap(messageResponse -> {
                    if (!messageResponse.success()) {
                        return Mono.just(new ResponseEntity<>(new MessageResponse(false,
                                messageResponse.message()), HttpStatus.BAD_REQUEST));
                    }
                    return rolePayload.verifyingRoleId(request.roleId())
                            .flatMap(verifyingResponse -> {
                                if (!verifyingResponse.success()) {
                                    return Mono.just(new ResponseEntity<>(new MessageResponse(false,
                                            verifyingResponse.message()), HttpStatus.BAD_REQUEST));
                                }
                                return rolePayload.exception(request)
                                        .flatMap(regexpResponse -> {
                                            if (!regexpResponse.success()) {
                                                return Mono.just(new ResponseEntity<>(new MessageResponse(false,
                                                        regexpResponse.message()), HttpStatus.BAD_REQUEST));
                                            }
                                            return roleEventHandler.update(request)
                                                    .flatMap(role -> {
                                                        if (role != null) {
                                                            return Mono.just(new ResponseEntity<>(new MessageResponse(true,
                                                                    RoleUtilsConstants.update), HttpStatus.OK));
                                                        } else {
                                                            return Mono.just(new ResponseEntity<>(new MessageResponse(false,
                                                                    "Échec de la mise à jour du rôle"), HttpStatus.INTERNAL_SERVER_ERROR));
                                                        }
                                                    });
                                        });
                            });
                });
    }
}
