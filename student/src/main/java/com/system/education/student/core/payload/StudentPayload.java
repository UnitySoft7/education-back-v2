package com.system.education.student.core.payload;

import reactor.core.publisher.Mono;

public interface StudentPayload {
    Mono<String> getCode();

    Mono<Boolean> isStudentCodeExist(String studentCode);
}
