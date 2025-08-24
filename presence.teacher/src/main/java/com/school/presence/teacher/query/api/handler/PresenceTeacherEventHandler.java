package com.school.presence.teacher.query.api.handler;

import com.school.presence.teacher.cmd.api.command.PresenceTeacherCreatedCommand;
import com.school.presence.teacher.core.model.PresenceTeacher;
import reactor.core.publisher.Mono;

public
interface PresenceTeacherEventHandler {
    Mono<PresenceTeacher> create ( PresenceTeacherCreatedCommand command );

}
