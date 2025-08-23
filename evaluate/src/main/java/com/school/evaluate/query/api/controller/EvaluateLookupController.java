package com.school.evaluate.query.api.controller;

import com.school.evaluate.cmd.api.query.*;
import com.school.evaluate.query.api.dto.AllLookupEvaluateMaxResponse;
import com.school.evaluate.query.api.dto.AllLookupEvaluateResponse;
import com.school.evaluate.query.api.dto.LookupEvaluateResponse;
import com.school.evaluate.query.api.handler.EvaluateQueryHandler;
import com.school.evaluate.query.api.repository.EvaluateRepository;
import com.school.evaluate.query.api.response.EvaluateMaxResponse;
import com.school.evaluate.query.api.response.EvaluateResponse;
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
@RequestMapping(path = "/api/v1/education/evaluate/lookup-evaluate/")
@Tag(name = "Evaluate", description = "Data REST API for class resource")
public class EvaluateLookupController {
    private final EvaluateQueryHandler evaluateQueryHandler;
    private final EvaluateRepository evaluateRepository;

    @Operation(summary = "Retrieve data Evaluate")
    @GetMapping(path = "get-all-evaluates", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupEvaluateResponse>> show() {
        return evaluateQueryHandler.findAllEvaluates().collectList().map(Evaluate -> new AllLookupEvaluateResponse(true, Evaluate, "Bien extrait")).map(ResponseEntity::ok);
    }
    @Operation(summary = "Retrieve data: Evaluate notes by student code")
    @PutMapping(path = "get-max-note-evaluate-by-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupEvaluateMaxResponse>> showsByCode(
            @Valid @RequestBody FindByStudentCourseTrimesterYearQuery query) {

        String studentCode = query.studentCode(); // Récupère le code de l'étudiant

        Mono<List<EvaluateMaxResponse>> maxNotesMono = evaluateQueryHandler.findMaxEvaluateByCode(studentCode).collectList();
        Mono<Double> sumNotesMono = evaluateQueryHandler.findSumNotesByCode(studentCode);
        Mono<EvaluateResponse> fullNameMono = evaluateQueryHandler.findAllExaminationsBySCSY(query);
        Mono<EvaluateResponse> ponderationMono = evaluateQueryHandler.findAllEvaluateByStudent(studentCode).next();

        return Mono.zip(maxNotesMono, sumNotesMono, fullNameMono, ponderationMono)
                .flatMap(tuple -> {
                    List<EvaluateMaxResponse> maxNotesList = tuple.getT1();
                    Double sumNotes = tuple.getT2();
                    EvaluateResponse fullNameResponse = tuple.getT3();
                    EvaluateResponse ponderationResponse = tuple.getT4();

                    double totalMaxNote = maxNotesList.stream()
                            .mapToDouble(EvaluateMaxResponse::max)
                            .sum();

                    double ponderation = ponderationResponse.ponderation();
                    double finalNote = (totalMaxNote > 0)
                            ? (sumNotes * ponderation) / totalMaxNote
                            : 0.0;

                    double percentage = (ponderation > 0)
                            ? (finalNote / ponderation) * 100
                            : 0.0;

                    AllLookupEvaluateMaxResponse response = new AllLookupEvaluateMaxResponse(
                            true,
                            maxNotesList,
                            studentCode,
                            fullNameResponse.studentFullname(),
                            finalNote,
                            ponderation,
                            sumNotes,
                            totalMaxNote,
                            percentage,
                            "Bien extrait"
                    );

                    return Mono.just(ResponseEntity.ok(response));
                })
                .onErrorResume(ex -> {
                    AllLookupEvaluateMaxResponse errorResponse = new AllLookupEvaluateMaxResponse(
                            false,
                            null,
                            "",
                            "",
                            0,
                            0,
                            0,
                            0,
                            0,
                            "Erreur serveur : " + ex.getMessage()
                    );
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse));
                });
    }


    @Operation(summary = "Retrieve data Evaluate by trimester")
    @PutMapping(path = "get-all-evaluate-by-trimester", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupEvaluateResponse>> showByTri(@Valid @RequestBody FindByTrimesterQuery query) {
        return evaluateQueryHandler.findAllEvaluateByTrimester(query).collectList().flatMap(evaluateResponses -> {
            if (evaluateResponses.isEmpty()) {
                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AllLookupEvaluateResponse(false, evaluateResponses, "Aucune grille d'évaluation trouvée pour ce trimestre.")));
            } else {
                return Mono.just(ResponseEntity.ok(new AllLookupEvaluateResponse(true, evaluateResponses, "Bien extrait")));
            }
        }).onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AllLookupEvaluateResponse(false, List.of(), "Erreur serveur : " + ex.getMessage()))));
    }


    @Operation(summary = "Retrieve data Evaluate by establish class section prof semester year")
    @PutMapping(path = "get-all-evaluate-by-establish-class-section-prof-semester-year", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupEvaluateResponse>> showByECSPSY(@Valid @RequestBody FindByEstablishClassSectionProfCourseTrimesterYearQuery query) {
        return evaluateQueryHandler.findAllEvaluateByEstaClassSectionProfSemeYear(query).collectList().flatMap(evaluateResponses -> {
            if (evaluateResponses.isEmpty()) {
                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AllLookupEvaluateResponse(false, evaluateResponses, "Aucune grille d'évaluation trouvée pour ce trimestre.")));
            } else {
                return Mono.just(ResponseEntity.ok(new AllLookupEvaluateResponse(true, evaluateResponses, "Bien extrait")));
            }
        }).onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AllLookupEvaluateResponse(false, List.of(), "Erreur serveur : " + ex.getMessage()))));
    }



    @Operation(summary = "Retrieve data Evaluate by year")
    @PutMapping(path = "get-all-evaluate-by-year", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupEvaluateResponse>> showByTri(@Valid @RequestBody FindByYearQuery query) {
        return evaluateQueryHandler.findAllEvaluateByYear(query).collectList().flatMap(evaluateResponses -> {
            if (evaluateResponses.isEmpty()) {
                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AllLookupEvaluateResponse(false, evaluateResponses, "Aucune grille d'évaluation trouvée pour cette annee .")));
            } else {
                return Mono.just(ResponseEntity.ok(new AllLookupEvaluateResponse(true, evaluateResponses, "Bien extrait")));
            }
        }).onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AllLookupEvaluateResponse(false, List.of(), "Erreur serveur : " + ex.getMessage()))));
    }
    @Operation(summary = "Retrieve data Evaluate notes by code")
    @PutMapping(path = "get-all-evaluate-notes-by-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<LookupEvaluateResponse>> showByCode(@Valid @RequestBody FindByCodeQuery query) {
        return evaluateQueryHandler.findEvaluateByCode(query.code()).flatMap(evaluateResponse -> {
            if (evaluateResponse == null) {
                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LookupEvaluateResponse(false, null, "Aucune grille d'évaluation trouvée pour ce code.")));
            }
            return Mono.just(ResponseEntity.ok(new LookupEvaluateResponse(true, evaluateResponse, "Bien extrait")));
        }).switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LookupEvaluateResponse(false, null, "Aucune grille d'évaluation trouvée.")))).onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new LookupEvaluateResponse(false, null, "Erreur serveur : " + ex.getMessage()))));
    }


    @Operation(summary = "Retrieve data Evaluate notes by SCSY")
    @PutMapping(path = "get-all-evaluate-notes-by-student-course-semester-year", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<LookupEvaluateResponse>> showBySCSY(@Valid @RequestBody FindByStudentCourseTrimesterYearQuery query) {
        return evaluateQueryHandler.findAllExaminationsBySCSY(query).flatMap(evaluateResponse -> {
            if (evaluateResponse == null) {
                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LookupEvaluateResponse(false, null, "Aucune grille d'évaluation trouvée pour ce code.")));
            }
            return Mono.just(ResponseEntity.ok(new LookupEvaluateResponse(true, evaluateResponse, "Bien extrait")));
        }).switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LookupEvaluateResponse(false, null, "Aucune grille d'évaluation trouvée.")))).onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new LookupEvaluateResponse(false, null, "Erreur serveur : " + ex.getMessage()))));
    }

}























//    @Operation(summary = "Retrieve data Evaluate by ESCCT")
//    @PutMapping(path = "get-all-evaluate-by-ESCCT", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Mono<ResponseEntity<AllLookupEvaluateResponse>> showByESCCT(@Valid @RequestBody FindByCodeQuery query) {
//        return evaluateQueryHandler.findAllEvaluateByESCCT(query).collectList().flatMap(evaluateResponses -> {
//            if (evaluateResponses.isEmpty()) {
//                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AllLookupEvaluateResponse(false, evaluateResponses, "Aucune grille d'évaluation trouvée pour ce professeur .")));
//            } else {
//                return Mono.just(ResponseEntity.ok(new AllLookupEvaluateResponse(true, evaluateResponses, "Bien extrait")));
//            }
//        }).onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AllLookupEvaluateResponse(false, List.of(), "Erreur serveur : " + ex.getMessage()))));
//    }

//    @Operation(summary = "Retrieve data Evaluate by ESCS")
//    @PutMapping(path = "get-all-evaluatess-by-ESCS", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Mono<ResponseEntity<AllLookupEvaluateResponse>> showByESCS(@Valid @RequestBody String query) {
//        return evaluateQueryHandler.findAllEvaluateByECSCS(query).collectList().flatMap(evaluateResponses -> {
//            if (evaluateResponses.isEmpty()) {
//                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AllLookupEvaluateResponse(false, evaluateResponses, "Aucune grille d'évaluation trouvée pour cet eleve  .")));
//            } else {
//                return Mono.just(ResponseEntity.ok(new AllLookupEvaluateResponse(true, evaluateResponses, "Bien extrait")));
//            }
//        }).onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AllLookupEvaluateResponse(false, List.of(), "Erreur serveur : " + ex.getMessage()))));
//    }

//    @Operation(summary = "Retrieve data Evaluate by ESCC")
//    @PutMapping(path = "get-all-evaluates-by-ESCC", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Mono<ResponseEntity<AllLookupEvaluateResponse>> showByESCC(@Valid @RequestBody FindByCodeQuery query) {
//        return evaluateQueryHandler.findAllEvaluateByESCC(query).collectList().flatMap(evaluateResponses -> {
//            if (evaluateResponses.isEmpty()) {
//                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AllLookupEvaluateResponse(false, evaluateResponses, "Aucune grille d'évaluation trouvée pour ce cours   .")));
//            } else {
//                return Mono.just(ResponseEntity.ok(new AllLookupEvaluateResponse(true, evaluateResponses, "Bien extrait")));
//            }
//        }).onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AllLookupEvaluateResponse(false, List.of(), "Erreur serveur : " + ex.getMessage()))));
//    }
