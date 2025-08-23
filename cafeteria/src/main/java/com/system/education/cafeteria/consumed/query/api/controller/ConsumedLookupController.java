package com.system.education.cafeteria.consumed.query.api.controller;

import com.system.education.cafeteria.consumed.query.api.dto.AllLookupConsumedResponse;
import com.system.education.cafeteria.consumed.query.api.dto.LookupConsumedResponse;
import com.system.education.cafeteria.consumed.query.api.handler.ConsumedQueryHandler;
import com.system.education.cafeteria.consumed.query.api.query.ConsumedByCodeAndSemesterQuery;
import com.system.education.cafeteria.consumed.query.api.query.ConsumedByCodeQuery;
import com.system.education.cafeteria.product.query.api.query.ProductByCodeQuery;
import com.system.education.cafeteria.product.query.api.query.ProductByIdQuery;
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
@RequestMapping(path = "/api/v1/education/cafeteria/consumed-lookup/")
@Tag(name = "Consumed", description = "Data REST API for consumed resource")
public class ConsumedLookupController {
    private final ConsumedQueryHandler consumedQueryHandler;

    /**
     * This method is used to retrieve all consumed
     * @return the list of consumed
     */
    @Operation(summary = "Retrieve data consumed")
    @GetMapping(path = "get-consumed", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupConsumedResponse>> show() {
        return consumedQueryHandler.getConsumed()
                .collectList()
                .map(consumed ->
                        new AllLookupConsumedResponse(true, consumed))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a consumed by ID
     * @param query the ID of the consumed
     * @return the consumed with the specified ID
     */
    @Operation(summary = "Retrieve data consumed by ID")
    @PutMapping(path = "get-consumed-by-consumed-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getById(@Valid @RequestBody ProductByIdQuery query) {
        return consumedQueryHandler.getConsumedById(query)
                .map(consumed ->
                        new LookupConsumedResponse(true, consumed))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a consumed by student code
     * @param query the code of the student
     * @return the consumed with the specified code
     */
    @Operation(summary = "Retrieve data consumed by student")
    @PutMapping(path = "get-consumed-by-student", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByStudent(@Valid @RequestBody ConsumedByCodeQuery query) {
        return consumedQueryHandler.getConsumedByStudentCode(query)
                .collectList()
                .map(consumed ->
                        new AllLookupConsumedResponse(true, consumed))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a consumed by establishment code
     * @param query the code of the establishment
     * @return the consumed with the specified code
     */
    @Operation(summary = "Retrieve data consumed by establishment")
    @PutMapping(path = "get-consumed-by-establishment", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByEstablishment(@Valid @RequestBody ConsumedByCodeQuery query) {
        return consumedQueryHandler.getConsumedByEstablishment(query)
                .collectList()
                .map(consumed ->
                        new AllLookupConsumedResponse(true, consumed))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a consumed by student code and semester
     * @param query the code and semester of the student
     * @return the consumed with the specified code and semester
     */
    @Operation(summary = "Retrieve data consumed by student and semester")
    @PutMapping(path = "get-consumed-by-student-semester", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByStudentAndSemester(@Valid @RequestBody ConsumedByCodeAndSemesterQuery query) {
        return consumedQueryHandler.getConsumedByStudentAndSemester(query)
                .collectList()
                .map(consumed ->
                        new AllLookupConsumedResponse(true, consumed))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a consumed by establishment code and semester
     * @param query the code and semester of the establishment
     * @return the consumed with the specified code and semester
     */
    @Operation(summary = "Retrieve data consumed by establishment and semester")
    @PutMapping(path = "get-consumed-by-establishment-semester", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByEstablishmentAndSemester(@Valid @RequestBody ConsumedByCodeAndSemesterQuery query) {
        return consumedQueryHandler.getConsumedByEstablishmentAndSemester(query)
                .collectList()
                .map(consumed ->
                        new AllLookupConsumedResponse(true, consumed))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a consumed by student code and semester and status
     * @param query the code and semester and status of the student
     * @return the consumed with the specified code and semester and status
     */
    @Operation(summary = "Retrieve data consumed by student and semester and status")
    @PutMapping(path = "get-consumed-by-student-semester-status", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByStudentAndSemesterAndStatus(@Valid @RequestBody ConsumedByCodeAndSemesterQuery query) {
        return consumedQueryHandler.getConsumedByStudentAndSemesterAndStatus(query)
                .collectList()
                .map(consumed ->
                        new AllLookupConsumedResponse(true, consumed))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a consumed by establishment code and semester and status
     * @param query the code and semester and status of the establishment
     * @return the consumed with the specified code and semester and status
     */
    @Operation(summary = "Retrieve data consumed by establishment and semester and status")
    @PutMapping(path = "get-consumed-by-establishment-semester-status", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByEstablishmentAndSemesterAndStatus(@Valid @RequestBody ConsumedByCodeAndSemesterQuery query) {
        return consumedQueryHandler.getConsumedByEstablishmentAndSemesterAndStatus(query)
                .collectList()
                .map(consumed ->
                        new AllLookupConsumedResponse(true, consumed))
                .map(ResponseEntity::ok);
    }
}
