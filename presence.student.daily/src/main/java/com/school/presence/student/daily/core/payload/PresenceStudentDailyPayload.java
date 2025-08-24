package com.school.presence.student.daily.core.payload;


import reactor.core.publisher.Mono;

public
interface PresenceStudentDailyPayload {
    Mono<String> getCode();
}