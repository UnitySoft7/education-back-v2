package com.school.evaluate.cmd.api.controller;

import com.school.evaluate.cmd.api.command.EvaluateCreatedCommand;
import com.school.evaluate.core.dto.MessageResponse;
import com.school.evaluate.core.model.Evaluate;
import com.school.evaluate.core.payload.EvaluatePayload;
import com.school.evaluate.core.utils.MessageUtilsConstants;
import com.school.evaluate.query.api.handler.EvaluateEventHandler;
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

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/evaluate/create-evaluate")
@Tag(name = "Evaluate", description = "Data rest API for Evaluate resource")
public class CreateEvaluateController {
    private final EvaluateEventHandler EvaluateEventHandler;
    private final EvaluatePayload evaluatePayload;

    @Operation(summary = "Create Evaluate")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create(@Valid @RequestBody EvaluateCreatedCommand command) {

        return evaluatePayload.verifyEvaluate(command.evaluationCode(), command.studentCode())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(new MessageResponse(false, "The Evaluate with this evaluationCode and studentCode already exists")));
                    }
                    return evaluatePayload.verifyNote(command.note(), command.noteMax())
                            .flatMap(noteValid -> {
                                if (!noteValid) {
                                    return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(false, "Note invalide")));
                                }
                                Mono<Evaluate> disableMono = EvaluateEventHandler.create(command);
                                return disableMono.flatMap(disable -> {
                                    if (disable != null) {
                                        return Mono.just(ResponseEntity.ok().body(new MessageResponse(true, MessageUtilsConstants.created)));
                                    }
                                    return Mono.just(ResponseEntity.ok().body(new MessageResponse(false, MessageUtilsConstants.operation_failed)));
                                });
                            });
                });
    }
}



