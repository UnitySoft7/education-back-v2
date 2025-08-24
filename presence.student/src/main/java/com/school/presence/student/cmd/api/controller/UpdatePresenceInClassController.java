package com.school.presence.student.cmd.api.controller;

import com.school.presence.student.cmd.api.command.PresenceInClassUpdatedCommand;
import com.school.presence.student.core.dto.MessageResponse;
import com.school.presence.student.core.payload.PresenceInClassPayload;
import com.school.presence.student.core.utils.MessageUtilsConstants;
import com.school.presence.student.query.api.handler.PresenceInClassEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/presence-student/update-presence-student")
@Tag(name = "Presence Student", description = "Data rest API for update resource")
public class UpdatePresenceInClassController {
    private final PresenceInClassEventHandler updateEventHandler;
    private final PresenceInClassPayload updatePayload;

    @Operation(summary = "update")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> update(@RequestBody PresenceInClassUpdatedCommand command) {
        return Mono.just(ResponseEntity.ok().body(new MessageResponse(true, MessageUtilsConstants.updated)));
    }
}
