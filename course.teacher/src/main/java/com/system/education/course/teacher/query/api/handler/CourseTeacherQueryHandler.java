package com.system.education.course.teacher.query.api.handler;

import com.system.education.course.teacher.query.api.query.CourseTeacherByCodeQuery;
import com.system.education.course.teacher.query.api.query.CourseTeacherByIdQuery;
import com.system.education.course.teacher.query.api.response.CourseTeacherResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseTeacherQueryHandler {
    Flux<CourseTeacherResponse> getCourseTeachers();
    Mono<CourseTeacherResponse> getCourseTeacherById(CourseTeacherByIdQuery query);
    Mono<CourseTeacherResponse> getCourseTeacherByCode(CourseTeacherByCodeQuery query);
    Flux<CourseTeacherResponse> getCourseTeachersByEstablishmentCode(CourseTeacherByCodeQuery query);

    Flux<CourseTeacherResponse> getCourseTeachersByClass(
            CourseTeacherByCodeQuery query);

    Flux<CourseTeacherResponse> getCourseTeachersByTeacher(
            CourseTeacherByCodeQuery query);
}
