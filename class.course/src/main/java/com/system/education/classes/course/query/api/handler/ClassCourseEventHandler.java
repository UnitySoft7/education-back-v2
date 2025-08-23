package com.system.education.classes.course.query.api.handler;

import com.system.education.classes.course.cmd.api.command.ClassCourseCreatedCommand;
import com.system.education.classes.course.cmd.api.command.ClassCourseUpdatedCommand;
import com.system.education.classes.course.core.model.ClassCourse;
import reactor.core.publisher.Mono;

public interface ClassCourseEventHandler {
    Mono<ClassCourse> create(ClassCourseCreatedCommand command);

    Mono<ClassCourse> update(ClassCourseUpdatedCommand command);
}
