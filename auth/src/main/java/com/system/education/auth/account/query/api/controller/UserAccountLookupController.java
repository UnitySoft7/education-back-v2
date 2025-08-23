package com.system.education.auth.account.query.api.controller;

import com.system.education.auth.account.query.api.dto.LookupAuthRoleResponse;
import com.system.education.auth.account.query.api.dto.LookupUserAccountResponse;
import com.system.education.auth.account.query.api.query.UserByCodeQuery;
import com.system.education.auth.account.query.api.query.UserByIdQuery;
import com.system.education.auth.account.core.payload.UserAccountPayload;
import com.system.education.auth.account.query.api.dto.AllLookupUserAccountResponse;
import com.system.education.auth.account.query.api.handler.UserAccountQueryHandler;
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
@RequestMapping(path = "/api/v1/education/user-account/lookup-user-account/")
@Tag(name = "user-account", description = "Data REST API for user resource")
public class UserAccountLookupController {
    private final UserAccountQueryHandler userAccountQueryHandler;
    private final UserAccountPayload userAccountPayload;

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve data users")
    @GetMapping(path = "get-user-accounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupUserAccountResponse>> show () {
        return userAccountQueryHandler.findAllUser()
                .collectList()
                .map(userAccountResponses ->
                        new AllLookupUserAccountResponse(true, userAccountResponses))
                .map(ResponseEntity::ok);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve data user-account by ID")
    @PutMapping(path = "get-user-account-by-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getById (@Valid @RequestBody UserByIdQuery query) {
        return userAccountPayload.regexpUserAccountId(query.userId())
            .flatMap(messageResponse -> {
                if (!messageResponse.success()) {
                    return Mono.just(new ResponseEntity<>(messageResponse, HttpStatus.BAD_REQUEST));
                } else {
                    return userAccountPayload.verifyingUserAccountId(query.userId())
                        .flatMap(response -> {
                            if (!response.success()) {
                                return Mono.just(new ResponseEntity<>(response, HttpStatus.BAD_REQUEST));
                            } else {
                                return userAccountQueryHandler.findUserByID(query)
                                        .map(userAccountResponses ->
                                                new LookupUserAccountResponse(true, userAccountResponses))
                                        .map(ResponseEntity::ok);
                            }
                        });
                }
            });
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Verify username")
    @GetMapping(path = "verify-username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> verifyUsername(@PathVariable String username) {
        return userAccountQueryHandler.verifyUsername(username).
                map(ResponseEntity::ok);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve all global users")
    @GetMapping(path = "get-all-global-users", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupUserAccountResponse>> getAllGlobalUsers() {
        return userAccountQueryHandler.findAllGlobalUser()
                .collectList()
                .map(userAccountResponses ->
                        new AllLookupUserAccountResponse(true, userAccountResponses))
                .map(ResponseEntity::ok);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve all not global users")
    @GetMapping(path = "get-all-not-global-users", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupUserAccountResponse>> getAllNotGlobalUsers() {
        return userAccountQueryHandler.findAllNoGlobalUser()
                .collectList()
                .map(userAccountResponses ->
                        new AllLookupUserAccountResponse(true, userAccountResponses))
                .map(ResponseEntity::ok);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve all users by enterprise")
    @PutMapping(path = "get-all-users-by-enterprise", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupUserAccountResponse>> getAllUsersByEnterprise(@Valid @RequestBody UserByCodeQuery query) {
        return userAccountQueryHandler.findAllUserByEnterprise(query)
                .collectList()
                .map(userAccountResponses ->
                        new AllLookupUserAccountResponse(true, userAccountResponses))
                .map(ResponseEntity::ok);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve auth-role by user")
    @PutMapping(path = "get-auth-role-by-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<LookupAuthRoleResponse>> getAuthRoleByUser(@Valid @RequestBody UserByCodeQuery query) {
        return userAccountQueryHandler.findAuthRole(query)
                .map(userAccountResponses ->
                        new LookupAuthRoleResponse(true, userAccountResponses))
                .map(ResponseEntity::ok);
    }
}
