package com.system.education.auth.account.cmd.api.controller;

import com.system.education.auth.account.cmd.api.command.UserAccountUpdateRequest;
import com.system.education.auth.account.core.util.UserAccountUtilsConstants;
import com.system.education.auth.account.query.api.handler.UserAccountEventHandler;
import com.system.education.auth.core.dto.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/user-account/update-user-account")
@Tag(name = "user-account", description = "Data rest API for user-account resource")
public class UpdateUserAccountController {
    private final UserAccountEventHandler userAccountEventHandler;

    @Operation(summary = "Update user account's role")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> enable (@Valid @RequestBody UserAccountUpdateRequest request) {
        return userAccountEventHandler.updateRole(request)
                .flatMap(userAccount -> {
                    if (userAccount != null) {
                        return Mono.just(ResponseEntity.ok(new MessageResponse(true, UserAccountUtilsConstants.update_role)));
                    } else {
                        return Mono.just(new ResponseEntity<>(new MessageResponse(false, UserAccountUtilsConstants.id), HttpStatus.NOT_FOUND));
                    }
                });
    }
}
