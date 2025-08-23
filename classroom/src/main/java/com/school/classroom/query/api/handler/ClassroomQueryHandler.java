package com.school.classroom.query.api.handler;

import com.school.classroom.core.model.Classroom;
import com.school.classroom.query.api.response.ClassroomResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public
interface ClassroomQueryHandler {
    Flux<ClassroomResponse> findClassrooms();

    Mono<ClassroomResponse> findClassroomById(String clothId);

    Mono<ClassroomResponse> findClassroomByClassroomCode(String clothCode);

    Flux<ClassroomResponse> findByEstablishmentCode(String establishmentCode);

    ClassroomResponse getClassroom (Classroom classroom);
}