package com.system.education.infirmary.consumption.query.api.controller;

import com.system.education.infirmary.consumed.query.api.query.ConsumedByCodeQuery;
import com.system.education.infirmary.consumption.query.api.dto.AllLookupConsumptionResponse;
import com.system.education.infirmary.consumption.query.api.dto.LookupConsumptionResponse;
import com.system.education.infirmary.consumption.query.api.handler.ConsumptionQueryHandler;
import com.system.education.infirmary.consumption.query.api.query.ConsumedByCodeAndSemesterQuery;
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
@RequestMapping(path = "/api/v1/education/infirmary/consumption-lookup/")
@Tag(name = "Consumption", description = "Data REST API for consumption resource")
public class ConsumptionLookupController {
    private final ConsumptionQueryHandler consumptionQueryHandler;

    /**
     * This method is used to retrieve all consumptions
     * @return the list of consumptions
     */
    @Operation(summary = "Retrieve data consumptions")
    @GetMapping(path = "get-consumptions", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupConsumptionResponse>> show() {
        return consumptionQueryHandler.getConsumption()
                .collectList()
                .map(consumption ->
                        new AllLookupConsumptionResponse(true, consumption))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a consumption by ID
     * @param query the ID of the consumption
     * @return the consumption with the specified ID
     */
    @Operation(summary = "Retrieve data consumption by ID")
    @PutMapping(path = "get-consumption-by-consumption-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getById(@Valid @RequestBody ProductByIdQuery query) {
        return consumptionQueryHandler.getConsumptionById(query)
                .map(consumption ->
                        new LookupConsumptionResponse(true, consumption))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve all consumptions by student code
     * @param query the code of the student
     * @return the consumption with the specified code
     */
    @Operation(summary = "Retrieve data consumption by student")
    @PutMapping(path = "get-consumption-by-student", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByStudent(@Valid @RequestBody ConsumedByCodeQuery query) {
        return consumptionQueryHandler.getConsumptionByStudentCode(query)
                .collectList()
                .map(consumption ->
                        new AllLookupConsumptionResponse(true, consumption))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve all consumptions by establishment code
     * @param query the code of the establishment
     * @return the consumption with the specified code
     */
    @Operation(summary = "Retrieve data consumption by establishment")
    @PutMapping(path = "get-consumption-by-establishment", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByEstablishment(@Valid @RequestBody ConsumedByCodeQuery query) {
        return consumptionQueryHandler.getConsumptionByEstablishment(query)
                .collectList()
                .map(consumption ->
                        new AllLookupConsumptionResponse(true, consumption))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a consumption by student code and semester
     * @param query the code and semester of the student
     * @return the consumption with the specified code and semester
     */
    @Operation(summary = "Retrieve data consumption by student and semester")
    @PutMapping(path = "get-consumption-by-student-semester", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByStudentAndSemester(@Valid @RequestBody ConsumedByCodeAndSemesterQuery query) {
        return consumptionQueryHandler.getConsumptionByStudentAndSemester(query)
                .map(consumption ->
                        new LookupConsumptionResponse(true, consumption))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve all consumptions by establishment code and semester
     * @param query the code and semester of the establishment
     * @return the consumption with the specified code and semester
     */
    @Operation(summary = "Retrieve data consumption by establishment and semester")
    @PutMapping(path = "get-consumption-by-establishment-semester", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByEstablishmentAndSemester(@Valid @RequestBody ConsumedByCodeAndSemesterQuery query) {
        return consumptionQueryHandler.getConsumptionByEstablishmentAndSemester(query)
                .collectList()
                .map(consumption ->
                        new AllLookupConsumptionResponse(true, consumption))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve all consumptions by establishment code and semester and status
     * @param query the code and semester and status of the establishment
     * @return the consumption with the specified code and semester and status
     */
    @Operation(summary = "Retrieve data consumption by establishment and semester and status")
    @PutMapping(path = "get-consumption-by-establishment-semester-status", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByEstablishmentAndSemesterAndStatus(@Valid @RequestBody ConsumedByCodeAndSemesterQuery query) {
        return consumptionQueryHandler.getConsumptionByEstablishmentAndSemesterAndStatus(query)
                .collectList()
                .map(consumption ->
                        new AllLookupConsumptionResponse(true, consumption))
                .map(ResponseEntity::ok);
    }
}
