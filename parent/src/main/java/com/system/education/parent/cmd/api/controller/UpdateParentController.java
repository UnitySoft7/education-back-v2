package com.system.education.parent.cmd.api.controller;

import com.system.education.parent.cmd.api.command.ParentUpdatedCommand;
import com.system.education.parent.core.dto.MessageResponse;
import com.system.education.parent.core.payload.ParentPayload;
import com.system.education.parent.core.utils.ParentUtilsConstants;
import com.system.education.parent.query.api.handler.ParentEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/parent/update-parent")
@Tag(name = "Parent", description = "Data rest API for parent resource")
public class UpdateParentController {
    private final ParentEventHandler parentEventHandler;
    private final ParentPayload parentPayload;

    /**
     * This method is used to update a parent
     * @param command the command containing the parent information
     * @return the message for operation
     */
    @Operation(summary = "Update parent")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> update (@Valid @RequestBody ParentUpdatedCommand command) {
        return parentPayload.isParentCodeExist(command.parentCode())
                .flatMap(isExists -> {
                    if (isExists) {
                        return parentEventHandler.update(command)
                                .flatMap(parent -> {
                                    if (parent != null) {
                                        return Mono.just(ResponseEntity.ok()
                                                .body(new MessageResponse(true, ParentUtilsConstants.done)));
                                    }
                                    return Mono.just(ResponseEntity.badRequest()
                                            .body(new MessageResponse(false, ParentUtilsConstants.operation_failed)));
                                });
                    } else {
                        return Mono.just(ResponseEntity.badRequest()
                                .body(new MessageResponse(false, ParentUtilsConstants.code_not_found)));
                    }
                });

    }
}
