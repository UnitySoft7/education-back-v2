package com.school.examination.cmd.api.controller;

import com.school.examination.cmd.api.command.ExaminationCreatedCommand;
import com.school.examination.core.dto.MessageResponse;

import com.school.examination.core.payload.ExaminationPayload;
import com.school.examination.core.utils.MessageUtilsConstants;
import com.school.examination.query.api.handler.ExaminationEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/examination/create-examination")
@Tag(name = "Examination", description = "Data rest API for Examination resource")
public class CreateExaminationController {

    private final ExaminationEventHandler examinationEventHandler;
    private final ExaminationPayload examinationPayload;

    @Operation(summary = "Create Examination")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create(@Valid @RequestBody ExaminationCreatedCommand command) {
        return examinationPayload.verifyExamCodeAndStudent(command.examCode(), command.studentCode())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(new MessageResponse(false, "The Examination with this examCode and studentCode already exists")));
                    }
                    return examinationPayload.verifyNote(command.note(), command.noteMax())
                            .flatMap(noteValid -> {
                                if (!noteValid) {
                                    return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                            .body(new MessageResponse(false, "Note invalide")));
                                }
                                return examinationEventHandler.create(command)
                                        .map(result -> ResponseEntity.ok(new MessageResponse(true, MessageUtilsConstants.created)))
                                        .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(false, MessageUtilsConstants.operation_failed)));
                            });
                });

    }
}
