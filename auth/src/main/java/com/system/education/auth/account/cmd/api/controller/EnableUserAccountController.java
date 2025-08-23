package com.system.education.auth.account.cmd.api.controller;

import com.system.education.auth.account.cmd.api.command.ChangeUserStatusCommand;
import com.system.education.auth.core.dto.MessageResponse;
import com.system.education.auth.account.core.util.UserAccountUtilsConstants;
import com.system.education.auth.account.query.api.handler.UserAccountEventHandler;
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
@RequestMapping(path = "/api/v1/education/user-account/enable-user-account")
@Tag(name = "user-account", description = "Data rest API for user-account resource")
public class EnableUserAccountController {
    private final UserAccountEventHandler userAccountEventHandler;

    @Operation(summary = "Enable user account")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> enable (@Valid @RequestBody ChangeUserStatusCommand request) {
        return userAccountEventHandler.enable(request)
                .flatMap(userAccount -> {
                    if (userAccount != null) {
                        return Mono.just(ResponseEntity.ok(new MessageResponse(true, UserAccountUtilsConstants.enable)));
                    } else {
                        return Mono.just(new ResponseEntity<>(new MessageResponse(false, UserAccountUtilsConstants.id), HttpStatus.NOT_FOUND));
                    }
                });
    }
}
