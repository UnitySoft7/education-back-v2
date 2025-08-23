package com.system.education.teacher.query.api.handler;

import com.system.education.teacher.cmd.api.command.TeacherCreatedCommand;
import com.system.education.teacher.cmd.api.command.TeacherUpdatedCommand;
import com.system.education.teacher.cmd.api.command.TeacherUpdatedFunctionCommand;
import com.system.education.teacher.core.model.Teacher;
import reactor.core.publisher.Mono;

public interface TeacherEventHandler {
    Mono<Teacher> create(TeacherCreatedCommand command);

    Mono<Teacher> update(TeacherUpdatedCommand command);

    Mono<Teacher> updateFunction(TeacherUpdatedFunctionCommand command);
}
