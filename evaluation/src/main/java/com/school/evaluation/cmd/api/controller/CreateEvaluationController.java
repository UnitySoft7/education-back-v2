package com.school.evaluation.cmd.api.controller;

import com.school.evaluation.cmd.api.command.EvaluationCreatedCommand;
import com.school.evaluation.core.dto.MessageResponse;
import com.school.evaluation.core.model.Evaluation;
import com.school.evaluation.core.payload.EvaluationPayload;
import com.school.evaluation.core.utils.MessageUtilsConstants;
import com.school.evaluation.query.api.handler.EvaluationEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping(path = "/api/v1/education/evaluation/create-evaluation")
@Tag(name = "Evaluation", description = "Data rest API for Evaluation resource")
public class CreateEvaluationController {
    private final EvaluationEventHandler evaluationEventHandler;
    private final EvaluationPayload evaluationPayload;
    private static final Logger log = LoggerFactory.getLogger(CreateEvaluationController.class);
    @Operation(summary = "Create Evaluation")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create(@Valid @RequestBody EvaluationCreatedCommand command) {

//        return evaluationPayload.verifyESCCCode(command.ESCC()).flatMap(esccResponse -> {
//            if (!esccResponse.success()) {
//                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(false, "Cours code introuvable")));
//            }
//            return evaluationPayload.verifyESCCTCode(command.ESCCT()).flatMap(escctResponse -> {
//                if (!escctResponse.success()) {
//                    return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(false, "Prof code introuvable")));
//                }
//                return evaluationPayload.verifyESCSCode(command.ESCS()).flatMap(esccsResponse -> {
//                    if (!esccsResponse.success()) {
//                        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(false, "Eleve code introuvable")));
//                    }
//                    return evaluationPayload.verifyESCCode(command.ESC()).flatMap(escsResponse -> {
//                        if (!escsResponse.success()) {
//                            return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(false, "Section code introuvable")));
//                        }

                        return evaluationEventHandler.create(command).map(result ->
                                ResponseEntity.ok(new MessageResponse(true, MessageUtilsConstants.created)))
                                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                        .body(new MessageResponse(false, MessageUtilsConstants.operation_failed)));
//                    });
//                });
//            }).onErrorResume(throwable -> {
//                if (throwable instanceof feign.FeignException) {
//                    feign.FeignException feignEx = (feign.FeignException) throwable;
//                    log.error("Erreur Feign : status={} - message={}", feignEx.status(), feignEx.getMessage());
//                    HttpStatus status = switch (feignEx.status()) {
//                        case 400 -> HttpStatus.BAD_REQUEST;
//                        case 404 -> HttpStatus.NOT_FOUND;
//                        case 500 -> HttpStatus.INTERNAL_SERVER_ERROR;
//                        default -> HttpStatus.BAD_GATEWAY;
//                    };
//                    return Mono.just(ResponseEntity.status(status).body(new MessageResponse(false, "Erreur communication microservice : " + feignEx.getMessage())));
//                }
//                log.error("Erreur inconnue", throwable);
//                return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(false, "Erreur interne : " + throwable.getMessage())));
//            }).switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(false, "Réponse manquante ou erreur inattendue."))));
//        });
    }
}
