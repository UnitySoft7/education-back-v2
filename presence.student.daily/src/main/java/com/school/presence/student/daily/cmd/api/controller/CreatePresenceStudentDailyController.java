package com.school.presence.student.daily.cmd.api.controller;

import com.school.presence.student.daily.cmd.api.command.PresenceStudentDailyCreatedCommand;
import com.school.presence.student.daily.core.dto.MessageResponse;
import com.school.presence.student.daily.core.model.PresenceStudentDaily;
import com.school.presence.student.daily.core.payload.PresenceStudentDailyPayload;
import com.school.presence.student.daily.core.utils.MessageUtilsConstants;
import com.school.presence.student.daily.query.api.handler.PresenceStudentDailyEventHandler;
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
@RequestMapping(path = "/api/v1/education/presence-student-daily/create-presence-student-daily")
@Tag(name = "Presence Student Daily", description = "Data rest API for PresenceStudentDaily resource")
public class CreatePresenceStudentDailyController {
    private final PresenceStudentDailyEventHandler PresenceStudentDailyEventHandler;
    private final PresenceStudentDailyPayload presenceStudentDailyPayload;

    @Operation(summary = "Create PresenceStudentDaily")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create(@Valid @RequestBody PresenceStudentDailyCreatedCommand command) {
        Mono<PresenceStudentDaily> presenceStudentDailyMono = PresenceStudentDailyEventHandler.create(command);
        return presenceStudentDailyMono.flatMap(create -> {
            if (create != null) {
                return Mono.just(ResponseEntity.ok().body(new MessageResponse(true, MessageUtilsConstants.created)));
            }
            return Mono.just(ResponseEntity.ok().body(new MessageResponse(false, MessageUtilsConstants.operation_failed)));
        });
    }
}




