package com.school.presence.teacher.cmd.api.controller;

import com.school.presence.teacher.cmd.api.command.PresenceTeacherCreatedCommand;
import com.school.presence.teacher.core.dto.MessageResponse;
import com.school.presence.teacher.core.model.PresenceTeacher;
import com.school.presence.teacher.core.payload.PresenceTeacherPayload;
import com.school.presence.teacher.core.utils.MessageUtilsConstants;
import com.school.presence.teacher.query.api.handler.PresenceTeacherEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/presence-teacher/create-presence-teacher")
@Tag(name = "PresenceTeacher", description = "Data rest API for PresenceTeacher resource")
public class CreatePresenceTeacherController {
    private final PresenceTeacherEventHandler presenceTeacherEventHandler;
    private final PresenceTeacherPayload presenceTeacherPayload;
    @Operation(summary = "Create PresenceTeacher")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create(@Valid @RequestBody PresenceTeacherCreatedCommand command) {
        Mono<PresenceTeacher> courseMono = presenceTeacherEventHandler.create(command);
        return courseMono.flatMap(create -> {
            if (create != null) {
                return Mono.just(ResponseEntity.ok().body(new MessageResponse(true, MessageUtilsConstants.created)));
            }
            return Mono.just(ResponseEntity.ok().body(new MessageResponse(false, MessageUtilsConstants.operation_failed)));
        });
    }
}






