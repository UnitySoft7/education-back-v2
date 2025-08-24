package com.school.presence.student.cmd.api.controller;

import com.school.presence.student.cmd.api.command.PresenceInClassCreatedCommand;
import com.school.presence.student.core.dto.MessageResponse;
import com.school.presence.student.core.payload.PresenceInClassPayload;
import com.school.presence.student.core.utils.MessageUtilsConstants;
import com.school.presence.student.query.api.handler.PresenceInClassEventHandler;
import feign.FeignException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j@RestController
@RequestMapping("/api/v1/education/presence-student")
@RequiredArgsConstructor
@Validated
public class CreatePresenceInClassController {

    private final PresenceInClassEventHandler presenceInClassEventHandler;
    private final PresenceInClassPayload presenceInClassPayload;

    @Operation(summary = "Créer une présence en classe")
    @PostMapping(path = "/create-presence-student", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create(@Valid @RequestBody PresenceInClassCreatedCommand command) {
        return presenceInClassEventHandler.create(command)
                // Ne pas réemballer une ResponseEntity
                .map(response -> response)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new MessageResponse(false, MessageUtilsConstants.operation_failed)))
                .onErrorResume(throwable -> {
                    if (throwable instanceof FeignException feignEx) {
                        log.error("Erreur Feign : status={} - message={}", feignEx.status(), feignEx.getMessage());
                        HttpStatus status = switch (feignEx.status()) {
                            case 400 -> HttpStatus.BAD_REQUEST;
                            case 404 -> HttpStatus.NOT_FOUND;
                            case 500 -> HttpStatus.INTERNAL_SERVER_ERROR;
                            default -> HttpStatus.BAD_GATEWAY;
                        };
                        return Mono.just(ResponseEntity.status(status).body(
                                new MessageResponse(false, "Erreur microservice : " + feignEx.getMessage())
                        ));
                    }

                    log.error("Erreur inconnue dans le contrôleur", throwable);
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                            new MessageResponse(false, "Erreur interne : " + throwable.getMessage())
                    ));
                });
    }
}



//        return presenceInClassEventHandler.create(command).map(result -> ResponseEntity.ok(new MessageResponse(true, MessageUtilsConstants.created))).defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(false, MessageUtilsConstants.operation_failed)));
//        return presenceInClassPayload.verifyESCSCode(command.establishmentCode()).flatMap(esccResponse -> {
//                (command.scheduleCode(), "1").flatMap(esccResponse -> {
//            if (!esccResponse.success()) {
//                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(false, "Cours code introuvable")));
//            }

