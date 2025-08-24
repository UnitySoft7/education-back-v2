package com.school.dormitory.daily.core.payload;


import reactor.core.publisher.Mono;

public
interface DormitoryStudentDailyPayload {
    Mono<String> getCode();
}