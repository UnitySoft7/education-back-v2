package com.school.course.query.api.repository;

import com.school.course.core.model.Course;
import reactor.core.publisher.Mono;

public
interface CourseRepositories {
    Mono<Course> getLastCourse ( );
}
