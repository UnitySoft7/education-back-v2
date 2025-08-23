package com.school.classroom.query.api.repository;

import com.school.classroom.core.model.Classroom;
import reactor.core.publisher.Mono;

public
interface ClassroomRepositories {
    Mono<Classroom> getLastClassroom ( );
}
