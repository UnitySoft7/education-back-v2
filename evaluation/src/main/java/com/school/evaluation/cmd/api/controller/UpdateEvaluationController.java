package com.school.evaluation.cmd.api.controller;
import com.school.evaluation.cmd.api.command.EvaluationUpdatedCommand;
import com.school.evaluation.core.dto.MessageResponse;
import com.school.evaluation.core.model.Evaluation;
import com.school.evaluation.core.payload.EvaluationPayload;
import com.school.evaluation.core.utils.MessageUtilsConstants;
import com.school.evaluation.query.api.handler.EvaluationEventHandler;
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
@RequestMapping(path = "/api/v1/education/evaluation/update-evaluation")
@Tag(name = "Evaluation", description = "Data rest API for update resource")
public class UpdateEvaluationController {
    private final EvaluationEventHandler evaluationEventHandler;
    private final EvaluationPayload evaluationPayload;
    /**
     * This method is used to update the update state to available
     * @param command the command containing the update information
     * @return the message for operation
     */
    @Operation(summary = "update")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create (@Valid @RequestBody EvaluationUpdatedCommand command) {
        return evaluationPayload.isEvaluationCodeExist(command.evaluationCode ())
                .flatMap(exist -> {
                    if (exist) {
                        Mono<Evaluation> updateMono = evaluationEventHandler.update (command);
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
    }
}
