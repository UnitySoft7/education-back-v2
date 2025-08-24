package com.school.presence.student.core.payload;

import com.school.presence.student.query.api.dto.LookupEstablishmentSectionClassStudentResponse;
import reactor.core.publisher.Mono;

public interface PresenceInClassPayload {
    Mono<String> getCode();

    Mono<LookupEstablishmentSectionClassStudentResponse> verifyESCSCode(String code);

//    Mono<LookupScheduleIndexResponse> verifyScheduleCodeForIndex1(String code, String index);
}