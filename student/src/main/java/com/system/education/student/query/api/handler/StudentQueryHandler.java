package com.system.education.student.query.api.handler;

import com.system.education.student.query.api.query.StudentByCodeQuery;
import com.system.education.student.query.api.query.StudentByIdQuery;
import com.system.education.student.query.api.response.PresenceClassResponse;
import com.system.education.student.query.api.response.StudentResponse;
import org.springframework.core.io.UrlResource;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentQueryHandler {
    Flux<StudentResponse> getStudents();
    Mono<StudentResponse> getStudentById(StudentByIdQuery query);
    Mono<StudentResponse> getStudentByCode(StudentByCodeQuery query);
    Flux<StudentResponse> getStudentByParent(StudentByCodeQuery query);
    Flux<StudentResponse> getStudentByClass(StudentByCodeQuery query);
    Flux<StudentResponse> getStudentBySection(StudentByCodeQuery query);
    Flux<StudentResponse> getStudentByEstablishment(StudentByCodeQuery query);
    Mono<UrlResource> getStudentProfileByCode(StudentByCodeQuery query);
    Mono<UrlResource> getStudentExtractByCode(StudentByCodeQuery query);
    Mono<UrlResource> getStudentBulletinByCode(StudentByCodeQuery query);

    Mono<PresenceClassResponse> getPresenceStudentByClass(StudentByCodeQuery query);
}
