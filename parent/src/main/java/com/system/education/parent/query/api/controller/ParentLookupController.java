package com.system.education.parent.query.api.controller;

import com.system.education.parent.query.api.dto.AllLookupParentResponse;
import com.system.education.parent.query.api.dto.LookupParentResponse;
import com.system.education.parent.query.api.handler.ParentQueryHandler;
import com.system.education.parent.query.api.query.ParentByCodeQuery;
import com.system.education.parent.query.api.query.ParentByIdQuery;
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
@RequestMapping(path = "/api/v1/education/parent/parent-lookup/")
@Tag(name = "Parent", description = "Data REST API for parent resource")
public class ParentLookupController {
    private final ParentQueryHandler parentQueryHandler;

    /**
     * This method is used to retrieve all parents
     * @return the list of parents
     */
    @Operation(summary = "Retrieve data parents")
    @GetMapping(path = "get-parents", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupParentResponse>> show() {
        return parentQueryHandler.getParents()
                .collectList()
                .map(parents ->
                        new AllLookupParentResponse(true, parents))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a parent by ID
     * @param query the ID of the parent
     * @return the parent with the specified ID
     */
    @Operation(summary = "Retrieve data parent by ID")
    @PutMapping(path = "get-parent-by-parent-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getById(@Valid @RequestBody ParentByIdQuery query) {
        return parentQueryHandler.getParentById(query)
                .map(parent ->
                        new LookupParentResponse(true, parent))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a parent by parent code
     * @param query the code of the parent
     * @return the parent with the specified code
     */
    @Operation(summary = "Retrieve data parent by code")
    @PutMapping(path = "get-parent-by-parent-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<LookupParentResponse>> getByParentCode(@Valid @RequestBody ParentByCodeQuery query) {
        return parentQueryHandler.getParentByCode(query)
                .map(parent ->
                        new LookupParentResponse(true, parent))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest()
                        .body(new LookupParentResponse(false, null))));
    }
}
