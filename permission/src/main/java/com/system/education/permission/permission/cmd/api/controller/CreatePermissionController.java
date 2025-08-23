package com.system.education.permission.permission.cmd.api.controller;

import com.system.education.permission.core.dto.MessageResponse;
import com.system.education.permission.permission.core.utils.PermissionUtilsConstants;
import com.system.education.permission.permission.cmd.api.command.PermissionCreatedCommand;
import com.system.education.permission.permission.query.api.handler.PermissionEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/permission/create-permission")
@Tag(name = "Permission", description = "Data rest API for permission resource")
public class CreatePermissionController {
    private final PermissionEventHandler permissionEventHandler;

    /**
     * This method is used to create a new permission
     * @param command the command containing the permission information
     * @return the message for operation
     */
    @Operation(summary = "Create permission")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create (@Valid @RequestBody PermissionCreatedCommand command) {
        return permissionEventHandler.create(command)
                .flatMap(permission -> {
                    if (permission != null) {
                        return Mono.just(ResponseEntity.ok()
                                .body(new MessageResponse(true, PermissionUtilsConstants.done)));
                    }
                    return Mono.just(ResponseEntity.badRequest()
                            .body(new MessageResponse(false, PermissionUtilsConstants.operation_failed)));
                });
    }
}
