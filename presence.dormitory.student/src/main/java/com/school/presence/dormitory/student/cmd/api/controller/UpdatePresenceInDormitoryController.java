package com.school.presence.dormitory.student.cmd.api.controller;

import com.school.presence.dormitory.student.cmd.api.command.PresenceInDormitoryUpdatedCommand;
import com.school.presence.dormitory.student.core.dto.MessageResponse;
import com.school.presence.dormitory.student.core.payload.PresenceInDormitoryPayload;
import com.school.presence.dormitory.student.core.utils.MessageUtilsConstants;
import com.school.presence.dormitory.student.query.api.handler.PresenceInDormitoryEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/presence-dormitory-student/update-presence-dormitory-student")
@Tag(name = "Presence Dormitory Student", description = "Data rest API for update resource")
public class UpdatePresenceInDormitoryController {
    private final PresenceInDormitoryEventHandler updateEventHandler;
    private final PresenceInDormitoryPayload updatePayload;

    @Operation(summary = "update")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> update(@RequestBody PresenceInDormitoryUpdatedCommand command) {
        return Mono.just(ResponseEntity.ok().body(new MessageResponse(true, MessageUtilsConstants.updated)));
    }
}
