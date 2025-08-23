package com.school.course.core.payload;


import reactor.core.publisher.Mono;

public
interface CoursePayload {
    Mono<String> getCode();

    Mono<Boolean> isClassroomNameExist(String name);

    Mono<Boolean> isCourseCodeExist(String CourseCode);
}