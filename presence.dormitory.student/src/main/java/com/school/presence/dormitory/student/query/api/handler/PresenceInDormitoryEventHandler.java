package com.school.presence.dormitory.student.query.api.handler;

import com.school.presence.dormitory.student.cmd.api.command.PresenceInDormitoryCreatedCommand;
import com.school.presence.dormitory.student.cmd.api.command.PresenceInDormitoryUpdatedCommand;
import com.school.presence.dormitory.student.core.dto.MessageResponse;
import com.school.presence.dormitory.student.core.model.PresenceInDormitory;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public
interface PresenceInDormitoryEventHandler {
    Mono<ResponseEntity<MessageResponse>> create (PresenceInDormitoryCreatedCommand command );

    Mono<PresenceInDormitory> update(PresenceInDormitoryUpdatedCommand command);
}
