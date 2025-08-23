package com.school.classroom.core.payload;

import reactor.core.publisher.Mono;

public
interface ClassroomPayload {
    Mono<String> getCode ( );

    Mono<Boolean> isClassroomCodeExist ( String ClassroomCode );

    Mono<Boolean> isClassroomNameExist(String name);
}