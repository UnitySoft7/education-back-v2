package com.school.examination.cmd.api.controller;

import com.school.examination.cmd.api.command.ExaminationReclaimedCommand;
import com.school.examination.core.dto.MessageResponse;
import com.school.examination.core.model.Examination;
import com.school.examination.core.payload.ExaminationPayload;
import com.school.examination.core.utils.MessageUtilsConstants;
import com.school.examination.query.api.handler.ExaminationEventHandler;
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
@RequestMapping(path = "/api/v1/education/examination/reclaim-examination")
@Tag(name = "Examination", description = "Data rest API for reclaim resource")
public class ReclaimExamController {
    private final ExaminationEventHandler examinationEventHandler;
    private final ExaminationPayload examinationPayload;


    @Operation(summary = "reclaim")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create(@RequestBody ExaminationReclaimedCommand command) {
        return examinationPayload.verifyNote(command.note(), command.noteMax()).flatMap(noteValid -> {
            if (!noteValid) {
                return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(false, "Note invalide")));
            }

            return examinationPayload.isExaminationCodeExist(command.examinationCode()).flatMap(exist -> {
                if (exist) {
                    Mono<Examination> reclaimMono = examinationEventHandler.reclaim(command);
                    return reclaimMono.flatMap(reclaim -> {
                        if (reclaim != null) {
                            return Mono.just(ResponseEntity.ok().body(new MessageResponse(true, MessageUtilsConstants.reclaim)));
                        }
                        return Mono.just(ResponseEntity.ok().body(new MessageResponse(false, MessageUtilsConstants.operation_failed)));
                    });
                }
                return Mono.just(ResponseEntity.ok().body(new MessageResponse(false, MessageUtilsConstants.code_not_found)));
            });
        });
    }
}
