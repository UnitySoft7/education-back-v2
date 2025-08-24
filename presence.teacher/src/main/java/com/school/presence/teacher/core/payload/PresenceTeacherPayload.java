package com.school.presence.teacher.core.payload;

import reactor.core.publisher.Mono;

public interface PresenceTeacherPayload {
    Mono<String> getCode();

}