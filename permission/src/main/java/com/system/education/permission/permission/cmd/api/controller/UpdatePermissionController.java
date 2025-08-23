package com.system.education.permission.permission.cmd.api.controller;

import com.system.education.permission.core.dto.MessageResponse;
import com.system.education.permission.permission.core.utils.PermissionUtilsConstants;
import com.system.education.permission.permission.cmd.api.command.PermissionUpdatedCommand;
import com.system.education.permission.permission.core.payload.PermissionPayload;
import com.system.education.permission.permission.query.api.handler.PermissionEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/permission/update-permission")
@Tag(name = "Permission", description = "Data rest API for permission resource")
public class UpdatePermissionController {
    private final PermissionEventHandler permissionEventHandler;
    private final PermissionPayload permissionPayload;

    /**
     * This method is used to update a permission
     * @param command the command containing the permission information
     * @return the message for operation
     */
    @Operation(summary = "Update permission")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> update (@Valid @RequestBody PermissionUpdatedCommand command) {
        return permissionPayload.isPermissionCodeExist(command.permissionCode())
                .flatMap(isExists -> {
                    if (isExists) {
                        return permissionEventHandler.update(command)
                                .flatMap(permission -> {
                                    if (permission != null) {
                                        return Mono.just(ResponseEntity.ok()
                                                .body(new MessageResponse(true, PermissionUtilsConstants.done)));
                                    }
                                    return Mono.just(ResponseEntity.badRequest()
                                            .body(new MessageResponse(false, PermissionUtilsConstants.operation_failed)));
                                });
                    } else {
                        return Mono.just(ResponseEntity.badRequest()
                                .body(new MessageResponse(false, PermissionUtilsConstants.code_not_found)));
                    }
                });

    }
}
