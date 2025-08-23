package com.system.education.classes.course.query.api.repository;

import com.system.education.classes.course.core.model.ClassCourse;
import reactor.core.publisher.Mono;

public interface ClassCourseRepositories {
    Mono<ClassCourse> getLastClassCourse();
}
