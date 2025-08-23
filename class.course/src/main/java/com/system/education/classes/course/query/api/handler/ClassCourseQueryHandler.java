package com.system.education.classes.course.query.api.handler;

import com.system.education.classes.course.query.api.query.ClassCourseByCodeQuery;
import com.system.education.classes.course.query.api.query.ClassCourseByIdQuery;
import com.system.education.classes.course.query.api.response.ClassCourseResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClassCourseQueryHandler {
    Flux<ClassCourseResponse> getClassCourses();
    Mono<ClassCourseResponse> getClassCourseById(ClassCourseByIdQuery query);
    Mono<ClassCourseResponse> getClassCourseByCode(ClassCourseByCodeQuery query);
    Flux<ClassCourseResponse> getClassCoursesByEstablishmentCode(ClassCourseByCodeQuery query);
    Flux<ClassCourseResponse> getClassCoursesByClassCode(ClassCourseByCodeQuery query);
}
