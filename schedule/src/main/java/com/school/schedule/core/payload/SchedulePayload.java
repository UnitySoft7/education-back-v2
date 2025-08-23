package com.school.schedule.core.payload;

import com.school.schedule.query.api.dto.LookupEstablishmentSectionClassCourseResponse;
import com.school.schedule.query.api.response.LookupEstablishmentSectionClassResponse;
import reactor.core.publisher.Mono;

public
interface SchedulePayload {
    Mono<String> getCode ( );

    Mono<Boolean> isScheduleCodeExist ( String ScheduleCode );

    Mono<LookupEstablishmentSectionClassResponse> verifyECS(String code);

    Mono<LookupEstablishmentSectionClassCourseResponse> verifyECSC(String code);
}