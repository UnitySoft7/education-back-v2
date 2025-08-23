package com.system.education.auth.account.cmd.api.controller;

import com.system.education.auth.account.cmd.api.command.UserAccountCreatedRequest;
import com.system.education.auth.core.dto.MessageResponse;
import com.system.education.auth.core.dto.FieldsValidatorResponse;
import com.system.education.auth.account.core.payload.UserAccountPayload;
import com.system.education.auth.account.core.util.UserAccountUtilsConstants;
import com.system.education.auth.account.query.api.handler.UserAccountEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/user-account/create-user-account")
@Tag(name = "user-account", description = "Data REST API for user-account resource")
public class CreateUserAccountController {
    private final UserAccountEventHandler userAccountEventHandler;
    private final UserAccountPayload userAccountPayload;

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Create user account")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> create(@Valid @RequestBody UserAccountCreatedRequest request) {
        return userAccountPayload.existsUserAccountByUsername(request.username())
                .flatMap(exists -> {
                    if (exists) {
                        Map<String, String> errorValidator = new HashMap<>();
                        errorValidator.put("username", UserAccountUtilsConstants.username_exists);
                        return Mono.just(new ResponseEntity<>(new FieldsValidatorResponse(false,
                                errorValidator), HttpStatus.BAD_REQUEST));
                    } else {
                        return userAccountPayload.verifyAccountCode(request.accountCode())
                                .flatMap(messageResponse -> {
                                    if (!messageResponse.success()) {
                                        Map<String, String> errorValidator = new HashMap<>();
                                        errorValidator.put("accountCode", UserAccountUtilsConstants.user_code);
                                        return Mono.just(new ResponseEntity<>(new FieldsValidatorResponse(false,
                                                errorValidator), HttpStatus.BAD_REQUEST));
                                    } else {
                                        return userAccountPayload.verifyPassword(request.password(), request.verifyPassword())
                                                .flatMap(response -> {
                                                    if (!response.success()) {
                                                        Map<String, String> errorValidator = new HashMap<>();
                                                        errorValidator.put("verifyPassword", UserAccountUtilsConstants.verify_password);
                                                        return Mono.just(new ResponseEntity<>(new FieldsValidatorResponse(false,
                                                                errorValidator), HttpStatus.BAD_REQUEST));
                                                    } else {
                                                        return userAccountEventHandler.create(request)
                                                                .flatMap(userAccount -> {
                                                                    if (userAccount != null) {
                                                                        return Mono.just(new ResponseEntity<>(new MessageResponse(true,
                                                                                UserAccountUtilsConstants.create_password), HttpStatus.CREATED));
                                                                    } else {
                                                                        return Mono.just(new ResponseEntity<>(new MessageResponse(false,
                                                                                "Échec de la demande : l'utilisateur n'a pas été créé"),
                                                                                HttpStatus.INTERNAL_SERVER_ERROR));
                                                                    }
                                                                });
                                                    }
                                                });
                                    }
                                });
                    }
                });
    }
}
