package com.system.education.course.teacher.query.api.handler;

import com.system.education.course.teacher.cmd.api.command.CourseTeacherCreatedCommand;
import com.system.education.course.teacher.cmd.api.command.CourseTeacherUpdatedCommand;
import com.system.education.course.teacher.core.model.CourseTeacher;
import reactor.core.publisher.Mono;

public interface CourseTeacherEventHandler {
    Mono<CourseTeacher> create(CourseTeacherCreatedCommand command);

    Mono<CourseTeacher> update(CourseTeacherUpdatedCommand command);
}
