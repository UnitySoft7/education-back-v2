package com.school.evaluate.cmd.api.controller;

import com.school.evaluate.cmd.api.command.EvaluateReclaimedCommand;
import com.school.evaluate.core.dto.MessageResponse;
import com.school.evaluate.core.model.Evaluate;
import com.school.evaluate.core.payload.EvaluatePayload;
import com.school.evaluate.core.utils.MessageUtilsConstants;
import com.school.evaluate.query.api.handler.EvaluateEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/evaluate/reclaim-evaluate")
@Tag(name = "Evaluate", description = "Data rest API for reclaim resource")
public class ReclaimEvaluateController {
    private final EvaluateEventHandler reclaimEventHandler;
    private final EvaluatePayload evaluatePayload;

    @Operation(summary = "reclaim")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> reclaim(@RequestBody EvaluateReclaimedCommand command) {
        return evaluatePayload.verifyNote(command.note(), command.noteMax())
                .flatMap(noteValid -> {
                    if (!noteValid) {
                        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(false, "Note invalide")));
                    }

                    return evaluatePayload.isEvaluateCodeExist(command.evaluateCode())
                            .flatMap(exist -> {
                                if (exist) {
                                    Mono<Evaluate> reclaimMono = reclaimEventHandler.reclaim(command);
                                    return reclaimMono.flatMap(reclaim -> {
                                        if (reclaim != null) {
                                            return Mono.just(ResponseEntity.ok()
                                                    .body(new MessageResponse(true, MessageUtilsConstants.reclaim)));
                                        }
                                        return Mono.just(ResponseEntity.ok()
                                                .body(new MessageResponse(false, MessageUtilsConstants.operation_failed)));
                                    });
                                }
                                return Mono.just(ResponseEntity.ok()
                                        .body(new MessageResponse(false, MessageUtilsConstants.code_not_found)));
                            });
                });
    }
}
