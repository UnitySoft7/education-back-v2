package com.school.exam.query.api.controller;

import com.school.exam.cmd.api.query.FindByCodeAndYearQuery;
import com.school.exam.cmd.api.query.FindByStudentTrimesterYearQuery;
import com.school.exam.cmd.api.query.FindByTrimesterQuery;
import com.school.exam.cmd.api.query.FindByYearQuery;
import com.school.exam.query.api.dto.AllLookupExamResponse;
import com.school.exam.query.api.dto.LookupExamResponse;
import com.school.exam.query.api.handler.ExamQueryHandler;
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
@RequestMapping(path = "/api/v1/education/exam/lookup-exam/")
@Tag(name = "Exam", description = "Data REST API for class resource")
public class ExamLookupController {
    private final ExamQueryHandler examQueryHandler;

    @Operation(summary = "Retrieve data Exams")
    @GetMapping(path = "get-exams", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupExamResponse>> show() {
        return examQueryHandler.findExams().collectList().map(examResponses -> new AllLookupExamResponse(true, examResponses, "Bien extrait")).map(ResponseEntity::ok);
    }


    @Operation(summary = "Retrieve data exam by examCode")
    @GetMapping(path = "get-exam-by-exam-examCode/{examCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getExamByExamCode(@PathVariable("examCode") String code) {
        return examQueryHandler.findExamByExamCode(code).map(exam -> new LookupExamResponse(true, exam)).map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data Evaluate by trimester")
    @PutMapping(path = "get-all-exam-by-trimester", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupExamResponse>> showByTrimester(@Valid @RequestBody FindByTrimesterQuery query) {
        return examQueryHandler.findAllExamByTrimester(query).collectList().flatMap(examResponses -> {
            if (examResponses.isEmpty()) {
                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AllLookupExamResponse(false, examResponses, "Aucun examen trouvé pour ce trimestre .")));
            } else {
                return Mono.just(ResponseEntity.ok(new AllLookupExamResponse(true, examResponses, "Bien extrait")));
            }
        }).onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AllLookupExamResponse(false, List.of(), "Erreur serveur : " + ex.getMessage()))));
    }

    @Operation(summary = "Retrieve data Evaluate by year")
    @PutMapping(path = "get-all-exam-by-year", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupExamResponse>> showByYear(@Valid @RequestBody FindByYearQuery query) {
        return examQueryHandler.findAllExamByYear(query).collectList().flatMap(examResponses -> {
            if (examResponses.isEmpty()) {
                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AllLookupExamResponse(false, examResponses, "Aucun examen trouvé pour cette annee .")));
            } else {
                return Mono.just(ResponseEntity.ok(new AllLookupExamResponse(true, examResponses, "Bien extrait")));
            }
        }).onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AllLookupExamResponse(false, List.of(), "Erreur serveur : " + ex.getMessage()))));
    }
}
