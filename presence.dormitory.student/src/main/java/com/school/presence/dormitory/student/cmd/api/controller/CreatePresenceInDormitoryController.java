package com.school.presence.dormitory.student.cmd.api.controller;

import com.school.presence.dormitory.student.cmd.api.command.PresenceInDormitoryCreatedCommand;
import com.school.presence.dormitory.student.core.dto.MessageResponse;
import com.school.presence.dormitory.student.core.payload.PresenceInDormitoryPayload;
import com.school.presence.dormitory.student.core.utils.MessageUtilsConstants;
import com.school.presence.dormitory.student.query.api.handler.PresenceInDormitoryEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/presence-dormitory-student/create-presence-dormitory-student")
@Tag(name = "Presence Dormitory Student", description = "Data rest API for Presence resource")
public class CreatePresenceInDormitoryController {
    private final PresenceInDormitoryEventHandler presenceInDormitoryEventHandler;
    private final PresenceInDormitoryPayload presenceInDormitoryPayload;
    @Operation(summary = "Create PresenceInDormitory")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create(@Valid @RequestBody PresenceInDormitoryCreatedCommand command) {

        return presenceInDormitoryEventHandler.create(command).map(result -> ResponseEntity.ok(new MessageResponse(true, MessageUtilsConstants.created))).defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(false, MessageUtilsConstants.operation_failed)));
//        return presenceInDormitoryPayload.verifyESCSCode(command.prof()).flatMap(esccResponse -> {
////                (command.scheduleCode(), "1").flatMap(esccResponse -> {
//            if (!esccResponse.success()) {
//                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(false, "Cours code introuvable")));
//            }
//            return presenceInDormitoryEventHandler.create(command).map(result -> {
//                boolean success = result != null;
//                String message = success ? MessageUtilsConstants.created : MessageUtilsConstants.operation_failed;
//                return ResponseEntity.ok(new MessageResponse(success, message));
//            }).defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(false, MessageUtilsConstants.operation_failed)));
//        }).onErrorResume(throwable -> {
//            if (throwable instanceof FeignException feignEx) {
//                log.error("Erreur Feign : status={} - message={}", feignEx.status(), feignEx.getMessage());
//                HttpStatus status = switch (feignEx.status()) {
//                    case 400 -> HttpStatus.BAD_REQUEST;
//                    case 404 -> HttpStatus.NOT_FOUND;
//                    case 500 -> HttpStatus.INTERNAL_SERVER_ERROR;
//                    default -> HttpStatus.BAD_GATEWAY;
//                };
//                return Mono.just(ResponseEntity.status(status).body(new MessageResponse(false, "Erreur communication microservice : " + feignEx.getMessage())));
//            }
//            log.error("Erreur inconnue", throwable);
//            return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(false, "Erreur interne : " + throwable.getMessage())));
//        });
    }
}
