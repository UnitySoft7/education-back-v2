package com.system.education.teacher.query.api.repository;

import com.system.education.teacher.core.model.Teacher;
import reactor.core.publisher.Mono;

public interface TeacherRepositories {
    Mono<Teacher> getLastTeacher();
}
