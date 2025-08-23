package com.school.examination.query.api.controller;

import com.school.examination.cmd.api.query.*;
import com.school.examination.query.api.dto.AllLookupExaminationResponse;
import com.school.examination.query.api.dto.LookupExaminationResponse;
import com.school.examination.query.api.handler.ExaminationQueryHandler;
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
@RequestMapping(path = "/api/v1/education/examination/lookup-examination/")
@Tag(name = "Examination", description = "Data REST API for class resource")
public class ExaminationLookupController {
    private final ExaminationQueryHandler examinationQueryHandler;

    @Operation(summary = "Retrieve data Examinations ")
    @GetMapping(path = "get-all-examinations", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupExaminationResponse>> showAll() {
        return examinationQueryHandler.findExaminations().collectList().flatMap(examinationResponses -> {
            if (examinationResponses.isEmpty()) {
                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AllLookupExaminationResponse(false, examinationResponses, "Aucune grille d'examen trouvée pour ce professeur .")));
            } else {
                return Mono.just(ResponseEntity.ok(new AllLookupExaminationResponse(true, examinationResponses, "Bien extrait")));
            }
        }).onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AllLookupExaminationResponse(false, List.of(), "Erreur serveur : " + ex.getMessage()))));
    }

//    @Operation(summary = "Retrieve data Examination by ESCCT")
//    @PutMapping(path = "get-all-examination-by-ESCCT", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Mono<ResponseEntity<AllLookupExaminationResponse>> showByESCCT(@Valid @RequestBody FindByCodeQuery query) {
//        return examinationQueryHandler.findAllExaminationsByESCCT(query).collectList().flatMap(examinationResponses -> {
//            if (examinationResponses.isEmpty()) {
//                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AllLookupExaminationResponse(false, examinationResponses, "Aucune grille d'examen trouvée pour ce professeur .")));
//            } else {
//                return Mono.just(ResponseEntity.ok(new AllLookupExaminationResponse(true, examinationResponses, "Bien extrait")));
//            }
//        }).onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AllLookupExaminationResponse(false, List.of(), "Erreur serveur : " + ex.getMessage()))));
//    }
//
//    @Operation(summary = "Retrieve data Examination by ESCC")
//    @PutMapping(path = "get-all-examination-by-ESCC", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Mono<ResponseEntity<AllLookupExaminationResponse>> showByESCC(@Valid @RequestBody FindByCodeQuery query) {
//        return examinationQueryHandler.findAllExaminationsByESCC(query).collectList().flatMap(examinationResponses -> {
//            if (examinationResponses.isEmpty()) {
//                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AllLookupExaminationResponse(false, examinationResponses, "Aucune grille d'examen trouvée pour ce cours .")));
//            } else {
//                return Mono.just(ResponseEntity.ok(new AllLookupExaminationResponse(true, examinationResponses, "Bien extrait")));
//            }
//        }).onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AllLookupExaminationResponse(false, List.of(), "Erreur serveur : " + ex.getMessage()))));
//    }
//
//    @Operation(summary = "Retrieve data Examination by ESCS")
//    @PutMapping(path = "get-all-examination-by-ESCS", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Mono<ResponseEntity<AllLookupExaminationResponse>> showByESCS(@Valid @RequestBody FindByCodeQuery query) {
//        return examinationQueryHandler.findAllExaminationsByESCS(query).collectList().flatMap(examinationResponses -> {
//            if (examinationResponses.isEmpty()) {
//                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AllLookupExaminationResponse(false, examinationResponses, "Aucune grille d'examen trouvée pour cet eleve .")));
//            } else {
//                return Mono.just(ResponseEntity.ok(new AllLookupExaminationResponse(true, examinationResponses, "Bien extrait")));
//            }
//        }).onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AllLookupExaminationResponse(false, List.of(), "Erreur serveur : " + ex.getMessage()))));
//    }

    @Operation(summary = "Retrieve data Examination by trimester")
    @PutMapping(path = "get-all-examination-by-trimesters", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupExaminationResponse>> showByTrimester(@Valid @RequestBody FindByTrimesterQuery query) {
        return examinationQueryHandler.findAllExaminationsByTrimester(query).collectList().flatMap(examinationResponses -> {
            if (examinationResponses.isEmpty()) {
                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AllLookupExaminationResponse(false, examinationResponses, "Aucune grille d'examen trouvée pour ce trimestre .")));
            } else {
                return Mono.just(ResponseEntity.ok(new AllLookupExaminationResponse(true, examinationResponses, "Bien extrait")));
            }
        }).onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AllLookupExaminationResponse(false, List.of(), "Erreur serveur : " + ex.getMessage()))));
    }

    @Operation(summary = "Retrieve data Examination by year")
    @PutMapping(path = "get-all-examination-by-year", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupExaminationResponse>> showByYear(@Valid @RequestBody FindByYearQuery query) {
        return examinationQueryHandler.findAllExaminationsByYear(query).collectList().flatMap(examinationResponses -> {
            if (examinationResponses.isEmpty()) {
                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AllLookupExaminationResponse(false, examinationResponses, "Aucune grille d'examen trouvée pour cette annee .")));
            } else {
                return Mono.just(ResponseEntity.ok(new AllLookupExaminationResponse(true, examinationResponses, "Bien extrait")));
            }
        }).onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AllLookupExaminationResponse(false, List.of(), "Erreur serveur : " + ex.getMessage()))));
    }

    @Operation(summary = "Retrieve data Examination by ECSPCSY")
    @PutMapping(path = "get-all-examination-by-establi-class-section-prof-course-semester-year", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupExaminationResponse>> showByECSPCSY(@Valid @RequestBody FindByEstablishClassSectionProfCourseTrimesterYearQuery query) {
        return examinationQueryHandler.findAllExaminationsByECSP(query).collectList().flatMap(examinationResponses -> {
            if (examinationResponses.isEmpty()) {
                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AllLookupExaminationResponse(false, examinationResponses, "Aucune grille d'examen trouvée pour cette annee .")));
            } else {
                return Mono.just(ResponseEntity.ok(new AllLookupExaminationResponse(true, examinationResponses, "Bien extrait")));
            }
        }).onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AllLookupExaminationResponse(false, List.of(), "Erreur serveur : " + ex.getMessage()))));
    }

    @Operation(summary = "Retrieve Examination data by Student, Course, Trimester, and Year")
    @PutMapping(path = "get-examination-by-student-course-semester-year", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getExaminationByStudentCourseCode(
            @Valid @RequestBody FindByStudentCourseTrimesterYearQuery query) {

        return examinationQueryHandler.findAllExaminationsBySCSY(query)
                .map(examinations -> new LookupExaminationResponse(true, examinations))
                .map(ResponseEntity::ok);
    }


}
