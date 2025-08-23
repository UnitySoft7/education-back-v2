package com.school.exam.cmd.api.controller;

import com.school.exam.cmd.api.command.ExamCreatedCommand;
import com.school.exam.core.dto.MessageResponse;
import com.school.exam.core.payload.ExamPayload;
import com.school.exam.core.utils.MessageUtilsConstants;
import com.school.exam.query.api.handler.ExamEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(path = "/api/v1/education/exam/create-exam")
@Tag(name = "Exam", description = "Data rest API for Exam resource")
public class CreateExamController {
    private final ExamEventHandler examEventHandler;
    private static final Logger log = LoggerFactory.getLogger(CreateExamController.class);
    private final ExamPayload examPayload;

    @Operation(summary = "Create Exam")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create(@Valid @RequestBody ExamCreatedCommand command) {
        return examPayload.varifyNote(command.noteMax()).flatMap(noteIsValid -> {
            if (!noteIsValid) {
                return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(false, "Ce champ doit être un entier positif entre 1 et 999")));
            }
            return examEventHandler.create(command).map(result ->
                            ResponseEntity.ok(new MessageResponse(true, MessageUtilsConstants.created)))
                    .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(new MessageResponse(false, MessageUtilsConstants.operation_failed)));
        });
    }
}
