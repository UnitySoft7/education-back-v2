package com.system.education.infirmary.diagnosis.query.api.controller;

import com.system.education.infirmary.consumed.query.api.query.ConsumedByCodeQuery;
import com.system.education.infirmary.diagnosis.query.api.handler.DiagnosisQueryHandler;
import com.system.education.infirmary.diagnosis.query.api.dto.AllLookupDiagnosisResponse;
import com.system.education.infirmary.diagnosis.query.api.dto.LookupDiagnosisResponse;
import com.system.education.infirmary.diagnosis.query.api.query.DiagnosisByCodeAndSemesterQuery;
import com.system.education.infirmary.product.query.api.query.ProductByIdQuery;
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
@RequestMapping(path = "/api/v1/education/infirmary/diagnosis-lookup/")
@Tag(name = "Diagnosis", description = "Data REST API for diagnosis resource")
public class DiagnosisLookupController {
    private final DiagnosisQueryHandler diagnosisQueryHandler;

    /**
     * This method is used to retrieve all diagnosis
     * @return the list of diagnosis
     */
    @Operation(summary = "Retrieve data diagnosis")
    @GetMapping(path = "get-diagnosis", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupDiagnosisResponse>> show() {
        return diagnosisQueryHandler.getDiagnosis()
                .collectList()
                .map(diagnosis ->
                        new AllLookupDiagnosisResponse(true, diagnosis))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a diagnosis by ID
     * @param query the ID of the diagnosis
     * @return the diagnosis with the specified ID
     */
    @Operation(summary = "Retrieve data diagnosis by ID")
    @PutMapping(path = "get-diagnosis-by-diagnosis-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getById(@Valid @RequestBody ProductByIdQuery query) {
        return diagnosisQueryHandler.getDiagnosisById(query)
                .map(diagnosis ->
                        new LookupDiagnosisResponse(true, diagnosis))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve all diagnosis by student code
     * @param query the code of the student
     * @return the diagnosis with the specified code
     */
    @Operation(summary = "Retrieve data diagnosis by student")
    @PutMapping(path = "get-diagnosis-by-student", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByStudent(@Valid @RequestBody ConsumedByCodeQuery query) {
        return diagnosisQueryHandler.getDiagnosisByStudentCode(query)
                .collectList()
                .map(diagnosis ->
                        new AllLookupDiagnosisResponse(true, diagnosis))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve all diagnosis by establishment code
     * @param query the code of the establishment
     * @return the diagnosis with the specified code
     */
    @Operation(summary = "Retrieve data diagnosis by establishment")
    @PutMapping(path = "get-diagnosis-by-establishment", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByEstablishment(@Valid @RequestBody ConsumedByCodeQuery query) {
        return diagnosisQueryHandler.getDiagnosisByEstablishment(query)
                .collectList()
                .map(diagnosis ->
                        new AllLookupDiagnosisResponse(true, diagnosis))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a diagnosis by student code and semester
     * @param query the code and semester of the student
     * @return the diagnosis with the specified code and semester
     */
    @Operation(summary = "Retrieve data diagnosis by student and semester")
    @PutMapping(path = "get-diagnosis-by-student-semester", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByStudentAndSemester(@Valid @RequestBody DiagnosisByCodeAndSemesterQuery query) {
        return diagnosisQueryHandler.getDiagnosisByStudentAndSemester(query)
                .map(diagnosis ->
                        new LookupDiagnosisResponse(true, diagnosis))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve all diagnosis by establishment code and semester
     * @param query the code and semester of the establishment
     * @return the diagnosis with the specified code and semester
     */
    @Operation(summary = "Retrieve data diagnosis by establishment and semester")
    @PutMapping(path = "get-diagnosis-by-establishment-semester", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByEstablishmentAndSemester(@Valid @RequestBody DiagnosisByCodeAndSemesterQuery query) {
        return diagnosisQueryHandler.getDiagnosisByEstablishmentAndSemester(query)
                .collectList()
                .map(diagnosis ->
                        new AllLookupDiagnosisResponse(true, diagnosis))
                .map(ResponseEntity::ok);
    }
}
