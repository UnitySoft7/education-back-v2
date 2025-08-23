package com.system.education.permission.permission.query.api.controller;

import com.system.education.permission.permission.query.api.dto.AllLookupPermissionResponse;
import com.system.education.permission.permission.query.api.dto.LookupNameResponse;
import com.system.education.permission.permission.query.api.dto.LookupPermissionResponse;
import com.system.education.permission.permission.query.api.handler.PermissionQueryHandler;
import com.system.education.permission.permission.query.api.query.PermissionByCodeAndSemesterQuery;
import com.system.education.permission.permission.query.api.query.PermissionByCodeQuery;
import com.system.education.permission.permission.query.api.query.PermissionByIdQuery;
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
@RequestMapping(path = "/api/v1/education/permission/permission-lookup/")
@Tag(name = "Permission", description = "Data REST API for permission resource")
public class PermissionLookupController {
    private final PermissionQueryHandler permissionQueryHandler;

    /**
     * This method is used to retrieve all permissions
     * @return the list of permissions
     */
    @Operation(summary = "Retrieve data permissions")
    @GetMapping(path = "get-permissions", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupPermissionResponse>> show() {
        return permissionQueryHandler.getPermissions()
                .collectList()
                .map(typeResponses ->
                        new AllLookupPermissionResponse(true, typeResponses))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a permission by ID
     * @param query the ID of the permission
     * @return the permission with the specified ID
     */
    @Operation(summary = "Retrieve data permission by ID")
    @PutMapping(path = "get-permission-by-permission-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getById(@Valid @RequestBody PermissionByIdQuery query) {
        return permissionQueryHandler.getPermissionById(query)
                .map(typeResponse ->
                        new LookupPermissionResponse(true, typeResponse))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a permission by permission code
     * @param query the code of the permission
     * @return the permission with the specified code
     */
    @Operation(summary = "Retrieve data permission by code")
    @PutMapping(path = "get-permission-by-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<LookupPermissionResponse>> getByCode(@Valid @RequestBody PermissionByCodeQuery query) {
        return permissionQueryHandler.getPermissionByCode(query)
                .map(typeResponse ->
                        new LookupPermissionResponse(true, typeResponse))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest()
                        .body(new LookupPermissionResponse(false, null))));
    }

    /**
     * This method is used to retrieve a permission by establishment code
     * @param query the code of the establishment
     * @return the permission with the specified code
     */
    @Operation(summary = "Retrieve data permission by establishment")
    @PutMapping(path = "get-permission-by-establishment", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByEstablishment(@Valid @RequestBody PermissionByCodeQuery query) {
        return permissionQueryHandler.getPermissionByEstablishment(query)
                .collectList()
                .map(typeResponses ->
                        new AllLookupPermissionResponse(true, typeResponses))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data permission by establishment-and-semester")
    @PutMapping(path = "get-permission-by-establishment-and-semester", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByEstablishment(@Valid @RequestBody PermissionByCodeAndSemesterQuery query) {
        return permissionQueryHandler.getPermissionByEstablishmentAndSemester(query)
                .collectList()
                .map(typeResponses ->
                        new AllLookupPermissionResponse(true, typeResponses))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data permission by student-and-semester")
    @PutMapping(path = "get-permission-by-student-and-semester", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByStudent(@Valid @RequestBody PermissionByCodeAndSemesterQuery query) {
        return permissionQueryHandler.getPermissionByStudentAndSemester(query)
                .collectList()
                .map(typeResponses ->
                        new AllLookupPermissionResponse(true, typeResponses))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data permission by permission-type-and-semester")
    @PutMapping(path = "get-permission-by-permission-type-and-semester", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByPermissionType(@Valid @RequestBody PermissionByCodeAndSemesterQuery query) {
        return permissionQueryHandler.getPermissionByPermissionTypeAndSemester(query)
                .collectList()
                .map(typeResponses ->
                        new AllLookupPermissionResponse(true, typeResponses))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data permission type")
    @GetMapping(path = "get-type", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getName() {
        return Mono.just(ResponseEntity.ok(new LookupNameResponse(true, permissionQueryHandler.getName())));
    }
}
