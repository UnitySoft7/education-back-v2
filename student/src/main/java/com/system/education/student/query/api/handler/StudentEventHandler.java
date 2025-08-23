package com.system.education.student.query.api.handler;

import com.system.education.student.cmd.api.command.AddParentCommand;
import com.system.education.student.cmd.api.command.StudentCreatedCommand;
import com.system.education.student.cmd.api.command.StudentUpdatedCommand;
import com.system.education.student.core.model.Student;
import reactor.core.publisher.Mono;

public interface StudentEventHandler {
    Mono<Student> create(StudentCreatedCommand command);

    Mono<Student> update(StudentUpdatedCommand command);

    Mono<Student> addParent(AddParentCommand command);
}
