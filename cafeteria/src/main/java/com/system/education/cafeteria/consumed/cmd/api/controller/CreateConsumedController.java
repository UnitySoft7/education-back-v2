package com.system.education.cafeteria.consumed.cmd.api.controller;

import com.system.education.cafeteria.consumed.cmd.api.command.ConsumedCreatedCommand;
import com.system.education.cafeteria.core.dto.MessageResponse;
import com.system.education.cafeteria.core.utils.CafeteriaUtilsConstants;
import com.system.education.cafeteria.consumed.query.api.handler.ConsumedEventHandler;
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
@RequestMapping(path = "/api/v1/education/cafeteria/create-consumed")
@Tag(name = "Consumed", description = "Data rest API for consumed resource")
public class CreateConsumedController {
    private final ConsumedEventHandler consumedEventHandler;

    /**
     * This method is used to create a new consumed
     * @param command the command containing the consumed information
     * @return the message for operation
     */
    @Operation(summary = "Create consumed")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create (@Valid @RequestBody ConsumedCreatedCommand command) {
        return consumedEventHandler.create(command)
                .flatMap(consumed -> {
                    if (consumed != null) {
                        return Mono.just(ResponseEntity.ok()
                                .body(new MessageResponse(true, CafeteriaUtilsConstants.done)));
                    }
                    return Mono.just(ResponseEntity.badRequest()
                            .body(new MessageResponse(false, CafeteriaUtilsConstants.operation_failed)));
                });
    }
}
