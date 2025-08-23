package com.system.education.auth.role.query.api.controller;

import com.system.education.auth.role.core.payload.RolePayload;
import com.system.education.auth.role.query.api.dto.AllLookupNameResponse;
import com.system.education.auth.role.query.api.dto.AllLookupRoleResponse;
import com.system.education.auth.role.query.api.dto.LookupRoleResponse;
import com.system.education.auth.role.query.api.handler.RoleQueryHandler;
import com.system.education.auth.role.query.api.query.RoleByCodeQuery;
import com.system.education.auth.role.query.api.query.RoleByIdQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/role/lookup-role/")
@Tag(name = "role", description = "Data REST API for role resource")
public class RoleLookupController {
    private final RolePayload rolePayload;
    private final RoleQueryHandler roleQueryHandler;

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve data roles")
    @GetMapping(path = "get-roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupRoleResponse>> show () {
        return roleQueryHandler.findAllRole()
                .collectList()
                .map(roleResponses ->
                        new AllLookupRoleResponse(true, roleResponses))
                .map(ResponseEntity::ok);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve data roles")
    @GetMapping(path = "get-roles-not-global", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupRoleResponse>> showNotGlobal () {
        return roleQueryHandler.findAllRolesNotGlobal()
                .collectList()
                .map(roleResponses ->
                        new AllLookupRoleResponse(true, roleResponses))
                .map(ResponseEntity::ok);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve data roles")
    @GetMapping(path = "get-roles-global", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupRoleResponse>> showGlobal () {
        return roleQueryHandler.findAllRolesGlobal()
                .collectList()
                .map(roleResponses ->
                        new AllLookupRoleResponse(true, roleResponses))
                .map(ResponseEntity::ok);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve data role by enterprise")
    @PutMapping(path = "get-role-by-enterprise", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByEnterprise (@Valid @RequestBody RoleByCodeQuery query) {
        return roleQueryHandler.findRolesByEnterprise(query)
                .collectList()
                .map(roleResponses ->
                        new AllLookupRoleResponse(true, roleResponses))
                .map(ResponseEntity::ok);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve data role by role-ID")
    @PutMapping(path = "get-role-by-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getById (@Valid @RequestBody RoleByIdQuery query) {
        return rolePayload.regexpRoleId(query.roleId())
                .flatMap(messageResponse -> {
                    if (!messageResponse.success()) {
                        return Mono.just(new ResponseEntity<>(messageResponse, HttpStatus.BAD_REQUEST));
                    }
                    return rolePayload.verifyingRoleId(query.roleId())
                            .flatMap(response -> {
                                if (!response.success()) {
                                    return Mono.just(new ResponseEntity<>(response, HttpStatus.BAD_REQUEST));
                                }
                                return roleQueryHandler.findRoleByID(query)
                                        .map(roleResponses ->
                                                new LookupRoleResponse(true, roleResponses))
                                        .map(ResponseEntity::ok);
                            });
                });
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve data permissions")
    @GetMapping(path = "get-permissions", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupNameResponse>> showPermissionCommon () {
        return roleQueryHandler.getAllPermissionNames()
                .map(permissions ->
                        new AllLookupNameResponse(true, permissions))
                .map(ResponseEntity::ok);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve data global permissions")
    @GetMapping(path = "get-global-permissions", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupNameResponse>> showGlobalPermissionCommon () {
        return roleQueryHandler.getAllGlobalPermissionNames()
                .map(permissions ->
                        new AllLookupNameResponse(true, permissions))
                .map(ResponseEntity::ok);
    }
}
