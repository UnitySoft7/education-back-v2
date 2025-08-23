package com.school.evaluation.query.api.controller;

import com.school.evaluation.cmd.api.query.FindByCodeQuery;
import com.school.evaluation.cmd.api.query.FindByStudentTrimesterYearQuery;
import com.school.evaluation.cmd.api.query.FindByTrimesterQuery;
import com.school.evaluation.cmd.api.query.FindByYearQuery;
import com.school.evaluation.query.api.dto.AllLookupEvaluationResponse;
import com.school.evaluation.query.api.dto.LookupEvaluationResponse;
import com.school.evaluation.query.api.handler.EvaluationQueryHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/evaluation/lookup-evaluation/")
@Tag(name = "Evaluation", description = "Data REST API for class resource")
public class EvaluationLookupController {
    private final EvaluationQueryHandler evaluationQueryHandler;

    @Operation(summary = "Retrieve data Evaluation")
    @GetMapping(path = "get-evaluation", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupEvaluationResponse>> show() {
        return evaluationQueryHandler.findEvaluations().collectList().map(evaluationResponses -> new AllLookupEvaluationResponse(true, evaluationResponses, "Bien extrait")).map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data evaluation by code")
    @GetMapping(path = "get-evaluation-by-evaluation-code/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getEvaluationByEvaluationCode(@PathVariable("code") String code) {
        return evaluationQueryHandler.findEvaluationByCode(code).map(evaluation -> new LookupEvaluationResponse(true, evaluation)).map(ResponseEntity::ok);
    }

//    @Operation(summary = "Retrieve data evaluation by ESCCT")
//    @PutMapping(path = "get-all-evaluation-by-ESCCT", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Mono<ResponseEntity<AllLookupEvaluationResponse>> showByESCCT(@Valid @RequestBody FindByCodeQuery query) {
//        return evaluationQueryHandler.findAllEvaluationByECSCCT(query).collectList().flatMap(evaluateResponses -> {
//            if (evaluateResponses.isEmpty()) {
//                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AllLookupEvaluationResponse(false, evaluateResponses, "Aucune évaluation trouvée pour ce professeur .")));
//            } else {
//                return Mono.just(ResponseEntity.ok(new AllLookupEvaluationResponse(true, evaluateResponses, "Bien extrait")));
//            }
//        }).onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AllLookupEvaluationResponse(false, List.of(), "Erreur serveur : " + ex.getMessage()))));
//    }
//
//    @Operation(summary = "Retrieve data evaluation by ESC")
//    @PutMapping(path = "get-all-evaluation-by-ESC", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Mono<ResponseEntity<AllLookupEvaluationResponse>> showByESC(@Valid @RequestBody FindByCodeQuery query) {
//        return evaluationQueryHandler.findAllEvaluationByESC(query).collectList().flatMap(evaluateResponses -> {
//            if (evaluateResponses.isEmpty()) {
//                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AllLookupEvaluationResponse(false, evaluateResponses, "Aucune évaluation trouvée pour ce professeur .")));
//            } else {
//                return Mono.just(ResponseEntity.ok(new AllLookupEvaluationResponse(true, evaluateResponses, "Bien extrait")));
//            }
//        }).onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AllLookupEvaluationResponse(false, List.of(), "Erreur serveur : " + ex.getMessage()))));
//    }
//
//    @Operation(summary = "Retrieve data evaluation by ESCC")
//    @PutMapping(path = "get-all-evaluation-by-ESCC", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Mono<ResponseEntity<AllLookupEvaluationResponse>> showByESCC(@Valid @RequestBody FindByCodeQuery query) {
//        return evaluationQueryHandler.findAllEvaluationByESCC(query).collectList().flatMap(evaluateResponses -> {
//            if (evaluateResponses.isEmpty()) {
//                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AllLookupEvaluationResponse(false, evaluateResponses, "Aucune évaluation trouvée pour ce professeur .")));
//            } else {
//                return Mono.just(ResponseEntity.ok(new AllLookupEvaluationResponse(true, evaluateResponses, "Bien extrait")));
//            }
//        }).onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AllLookupEvaluationResponse(false, List.of(), "Erreur serveur : " + ex.getMessage()))));
//    }
//
//    @Operation(summary = "Retrieve data evaluation by student")
//    @PutMapping(path = "get-all-evaluation-by-student", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Mono<ResponseEntity<AllLookupEvaluationResponse>> showBystudent(@Valid @RequestBody FindByCodeQuery query) {
//        return evaluationQueryHandler.findAllEvaluationByECSCS(query).collectList().flatMap(evaluateResponses -> {
//            if (evaluateResponses.isEmpty()) {
//                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AllLookupEvaluationResponse(false, evaluateResponses, "Aucune évaluation trouvée pour ce professeur .")));
//            } else {
//                return Mono.just(ResponseEntity.ok(new AllLookupEvaluationResponse(true, evaluateResponses, "Bien extrait")));
//            }
//        }).onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AllLookupEvaluationResponse(false, List.of(), "Erreur serveur : " + ex.getMessage()))));
//    }

    @Operation(summary = "Retrieve data evaluation by year")
    @PutMapping(path = "get-all-evaluation-by-year", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupEvaluationResponse>> showByYear(@Valid @RequestBody FindByYearQuery query) {
        return evaluationQueryHandler.findAllEvaluationByYear(query).collectList().flatMap(evaluateResponses -> {
            if (evaluateResponses.isEmpty()) {
                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AllLookupEvaluationResponse(false, evaluateResponses, "Aucune évaluation trouvée pour ce professeur .")));
            } else {
                return Mono.just(ResponseEntity.ok(new AllLookupEvaluationResponse(true, evaluateResponses, "Bien extrait")));
            }
        }).onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AllLookupEvaluationResponse(false, List.of(), "Erreur serveur : " + ex.getMessage()))));
    }
    @Operation(summary = "Retrieve data evaluation by year")
    @PutMapping(path = "get-all-evaluation-by-prof-semestre-year", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupEvaluationResponse>> showBySSY(@Valid @RequestBody FindByStudentTrimesterYearQuery query) {
        return evaluationQueryHandler.findAllEvaluationByProfAndSemestreAndYear(query).collectList().flatMap(evaluateResponses -> {
            if (evaluateResponses.isEmpty()) {
                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AllLookupEvaluationResponse(false, evaluateResponses, "Aucune évaluation trouvée pour ce professeur .")));
            } else {
                return Mono.just(ResponseEntity.ok(new AllLookupEvaluationResponse(true, evaluateResponses, "Bien extrait")));
            }
        }).onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AllLookupEvaluationResponse(false, List.of(), "Erreur serveur : " + ex.getMessage()))));
    }


    @Operation(summary = "Retrieve data evaluation by trimester")
    @PutMapping(path = "get-all-evaluation-by-trimester", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupEvaluationResponse>> showByTrimester(@Valid @RequestBody FindByTrimesterQuery query) {
        return evaluationQueryHandler.findAllEvaluationByTrimester(query).collectList().flatMap(evaluateResponses -> {
            if (evaluateResponses.isEmpty()) {
                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AllLookupEvaluationResponse(false, evaluateResponses, "Aucune évaluation trouvée pour ce professeur .")));
            } else {
                return Mono.just(ResponseEntity.ok(new AllLookupEvaluationResponse(true, evaluateResponses, "Bien extrait")));
            }
        }).onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AllLookupEvaluationResponse(false, List.of(), "Erreur serveur : " + ex.getMessage()))));
    }
}