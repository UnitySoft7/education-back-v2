package com.school.classroom.query.api.handler;

import com.school.classroom.cmd.api.command.ClassroomCreatedCommand;
import com.school.classroom.cmd.api.command.ClassroomUpdatedCommand;
import com.school.classroom.core.model.Classroom;
import reactor.core.publisher.Mono;

public
interface ClassroomEventHandler {
    Mono<Classroom> create (ClassroomCreatedCommand command );

    Mono<Classroom> update(ClassroomUpdatedCommand command);
}
