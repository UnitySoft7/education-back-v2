package com.system.education.parent.cmd.api.controller;

import com.system.education.parent.cmd.api.command.ParentCreatedCommand;
import com.system.education.parent.core.dto.FieldsValidatorResponse;
import com.system.education.parent.core.dto.MessageResponse;
import com.system.education.parent.core.payload.ParentPayload;
import com.system.education.parent.core.utils.ParentUtilsConstants;
import com.system.education.parent.query.api.handler.ParentEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/parent/create-parent")
@Tag(name = "Parent", description = "Data rest API for parent resource")
public class CreateParentController {
    private final ParentEventHandler parentEventHandler;
    private final ParentPayload parentPayload;

    /**
     * This method is used to create a new parent
     * @param command the command containing the parent information
     * @return the message for operation
     */
    @Operation(summary = "Create parent")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> create (@Valid @RequestBody ParentCreatedCommand command) {
        return parentPayload.verifyPassword(command.password(), command.verifyPassword())
                .flatMap(messageResponse -> {
                    if (!messageResponse.success()) {
                        Map<String, String> errorValidator = new HashMap<>();
                        errorValidator.put("verifyPassword", ParentUtilsConstants.verify_password);
                        return Mono.just(new ResponseEntity<>(new FieldsValidatorResponse(false,
                                errorValidator), HttpStatus.BAD_REQUEST));
                    }
                    return parentEventHandler.create(command)
                            .flatMap(parent -> {
                                if (parent != null) {
                                    return Mono.just(ResponseEntity.ok()
                                            .body(new MessageResponse(true, ParentUtilsConstants.done)));
                                }
                                return Mono.just(ResponseEntity.badRequest()
                                        .body(new MessageResponse(false, ParentUtilsConstants.operation_failed)));
                            });
                });

    }
}
