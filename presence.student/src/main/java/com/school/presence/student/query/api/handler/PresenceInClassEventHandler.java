package com.school.presence.student.query.api.handler;

import com.school.presence.student.cmd.api.command.PresenceInClassCreatedCommand;
import com.school.presence.student.cmd.api.command.PresenceInClassUpdatedCommand;
import com.school.presence.student.core.dto.MessageResponse;
import com.school.presence.student.core.model.PresenceInClass;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public
interface PresenceInClassEventHandler {
    Mono<ResponseEntity<MessageResponse>> create (PresenceInClassCreatedCommand command );

    Mono<PresenceInClass> update(PresenceInClassUpdatedCommand command);
}
