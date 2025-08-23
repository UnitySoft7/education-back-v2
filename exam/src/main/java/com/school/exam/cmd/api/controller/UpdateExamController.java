package com.school.exam.cmd.api.controller;
import com.school.exam.cmd.api.command.ExamUpdatedCommand;
import com.school.exam.core.dto.MessageResponse;
import com.school.exam.core.model.Exam;
import com.school.exam.core.payload.ExamPayload;
import com.school.exam.core.utils.MessageUtilsConstants;
import com.school.exam.query.api.handler.ExamEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/exam/update-exam")
@Tag(name = "Exam", description = "Data rest API for update resource")
public class UpdateExamController {
    private final ExamEventHandler examEventHandler;
    private final ExamPayload examPayload;
    /**
     * This method is used to update the update state to available
     * @param command the command containing the update information
     * @return the message for operation
     */
    @Operation(summary = "update")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create (@RequestBody ExamUpdatedCommand command) {
        return examPayload.varifyNote(command.noteMax()).flatMap(noteIsValid -> {
            if (!noteIsValid) {
                return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(false, "Ce champ doit être un entier positif entre 1 et 999")));
            }
            return examPayload.isExamCodeExist(command.examCode())
                    .flatMap(exist -> {
                        if (exist) {
                            Mono<Exam> updateMono = examEventHandler.update(command);
                            return updateMono.flatMap(update -> {
                                if (update != null) {
                                    return Mono.just(ResponseEntity.ok()
                                            .body(new MessageResponse(true, MessageUtilsConstants.updated)));
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
