package com.school.presence.student.daily.query.api.handler;

import com.school.presence.student.daily.cmd.api.command.PresenceStudentDailyCreatedCommand;
import com.school.presence.student.daily.core.model.PresenceStudentDaily;
import reactor.core.publisher.Mono;

public
interface PresenceStudentDailyEventHandler {
    Mono<PresenceStudentDaily> create ( PresenceStudentDailyCreatedCommand command );

}
