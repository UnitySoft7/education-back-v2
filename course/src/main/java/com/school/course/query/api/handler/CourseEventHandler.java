package com.school.course.query.api.handler;

import com.school.course.cmd.api.command.CourseCreatedCommand;
import com.school.course.cmd.api.command.CourseUpdatedCommand;
import com.school.course.core.model.Course;
import reactor.core.publisher.Mono;

public
interface CourseEventHandler {
    Mono<Course> create ( CourseCreatedCommand command );

    Mono<Course> update(CourseUpdatedCommand command);
}
