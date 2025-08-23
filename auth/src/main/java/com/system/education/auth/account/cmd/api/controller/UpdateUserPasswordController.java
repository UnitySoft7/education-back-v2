package com.system.education.auth.account.cmd.api.controller;

import com.system.education.auth.account.cmd.api.command.PasswordUpdatedRequest;
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
@RequestMapping(path = "/api/v1/education/user-account/update-user-password")
@Tag(name = "user-account", description = "Data REST API for user-account resource")
public class UpdateUserPasswordController {
    private final UserAccountEventHandler userAccountEventHandler;
    private final UserAccountPayload userAccountPayload;

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update user password")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> update(@Valid @RequestBody PasswordUpdatedRequest request) {
            return userAccountPayload.verifyOldPassword(request.accountCode(), request.oldPassword())
                    .flatMap(messageResponse -> {
                        if (!messageResponse.success()) {
                            Map<String, String> errorValidator = new HashMap<>();
                            errorValidator.put("oldPassword", UserAccountUtilsConstants.old_password);
                            return Mono.just(new ResponseEntity<>(new FieldsValidatorResponse(false, errorValidator), HttpStatus.BAD_REQUEST));
                        } else {
                            return userAccountPayload.verifyPassword(request.newPassword(), request.verifyPassword())
                                    .flatMap(passwordResponse -> {
                                        if (!passwordResponse.success()) {
                                            Map<String, String> errorValidator = new HashMap<>();
                                            errorValidator.put("verifyPassword", UserAccountUtilsConstants.verify_password);
                                            return Mono.just(new ResponseEntity<>(new FieldsValidatorResponse(false, errorValidator), HttpStatus.BAD_REQUEST));
                                        } else {
                                            return userAccountEventHandler.updatePassword(request)
                                                    .map(userAccount -> {
                                                        if (userAccount != null) {
                                                            return ResponseEntity.ok(new MessageResponse(true,
                                                                    UserAccountUtilsConstants.update_password));
                                                        } else {
                                                            return new ResponseEntity<>(new MessageResponse(false,
                                                                    UserAccountUtilsConstants.id), HttpStatus.NOT_FOUND);
                                                        }
                                                    });
                                        }
                                    });
                        }
                    });
    }
}
