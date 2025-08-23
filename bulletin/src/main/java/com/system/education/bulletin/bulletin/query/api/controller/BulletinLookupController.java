package com.system.education.bulletin.bulletin.query.api.controller;

import com.system.education.bulletin.bulletin.query.api.dto.AllLookupBulletinResponse;
import com.system.education.bulletin.bulletin.query.api.dto.LookupBulletinResponse;
import com.system.education.bulletin.bulletin.query.api.handler.BulletinQueryHandler;
import com.system.education.bulletin.bulletin.query.api.query.BulletinByCodeAndSemesterQuery;
import com.system.education.bulletin.bulletin.query.api.query.BulletinByCodeQuery;
import com.system.education.bulletin.bulletin.query.api.query.BulletinByIdQuery;
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
@RequestMapping(path = "/api/v1/education/bulletin/bulletin-lookup/")
@Tag(name = "Bulletin", description = "Data REST API for bulletin resource")
public class BulletinLookupController {
    private final BulletinQueryHandler bulletinQueryHandler;

    /**
     * This method is used to retrieve all bulletins
     * @return the list of bulletins
     */
    @Operation(summary = "Retrieve data bulletins")
    @GetMapping(path = "get-bulletins", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupBulletinResponse>> show() {
        return bulletinQueryHandler.getBulletins()
                .collectList()
                .map(bulletinResponses ->
                        new AllLookupBulletinResponse(true, bulletinResponses))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a bulletin by ID
     * @param query the ID of the bulletin
     * @return the bulletin with the specified ID
     */
    @Operation(summary = "Retrieve data bulletin by ID")
    @PutMapping(path = "get-bulletin-by-bulletin-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getById(@Valid @RequestBody BulletinByIdQuery query) {
        return bulletinQueryHandler.getBulletinById(query)
                .map(bulletinResponse ->
                        new LookupBulletinResponse(true, bulletinResponse))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a bulletin by bulletin code
     * @param query the code of the bulletin
     * @return the bulletin with the specified code
     */
    @Operation(summary = "Retrieve data bulletin by code")
    @PutMapping(path = "get-bulletin-by-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<LookupBulletinResponse>> getByCode(@Valid @RequestBody BulletinByCodeQuery query) {
        return bulletinQueryHandler.getBulletinByCode(query)
                .map(bulletinResponse ->
                        new LookupBulletinResponse(true, bulletinResponse))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest()
                        .body(new LookupBulletinResponse(false, null))));
    }

    @Operation(summary = "Retrieve data bulletin by student")
    @PutMapping(path = "get-bulletin-by-student", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<LookupBulletinResponse>> getByStudent(@Valid @RequestBody BulletinByCodeAndSemesterQuery query) {
        return bulletinQueryHandler.getBulletinByStudent(query)
                .map(bulletinResponse ->
                        new LookupBulletinResponse(true, bulletinResponse))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest()
                        .body(new LookupBulletinResponse(false, null))));
    }

    @Operation(summary = "Retrieve data bulletin by class")
    @PutMapping(path = "get-bulletin-by-class", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByClass(@Valid @RequestBody BulletinByCodeAndSemesterQuery query) {
        return bulletinQueryHandler.getBulletinByClass(query)
                .collectList()
                .map(bulletinResponses ->
                        new AllLookupBulletinResponse(true, bulletinResponses))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a bulletin by establishment code
     * @param query the code of the establishment
     * @return the bulletin with the specified code
     */
    @Operation(summary = "Retrieve data bulletin by establishment")
    @PutMapping(path = "get-bulletin-by-establishment", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByEstablishment(@Valid @RequestBody BulletinByCodeQuery query) {
        return bulletinQueryHandler.getBulletinByEstablishment(query)
                .collectList()
                .map(bulletinResponses ->
                        new AllLookupBulletinResponse(true, bulletinResponses))
                .map(ResponseEntity::ok);
    }
}
