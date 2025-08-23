package com.system.education.course.teacher.query.api.repository;

import com.system.education.course.teacher.core.model.CourseTeacher;
import reactor.core.publisher.Mono;

public interface CourseTeacherRepositories {
    Mono<CourseTeacher> getLastEstablishmentSectionClassCourse();
}
