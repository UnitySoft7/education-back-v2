package com.system.education.student.query.api.repository;

import com.system.education.student.core.model.Student;
import reactor.core.publisher.Mono;

public interface StudentRepositories {
    Mono<Student> getLastStudent();
}
