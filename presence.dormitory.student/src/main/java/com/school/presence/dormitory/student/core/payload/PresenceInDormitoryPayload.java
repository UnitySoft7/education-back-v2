package com.school.presence.dormitory.student.core.payload;

import com.school.presence.dormitory.student.query.api.dto.LookupEstablishmentSectionClassStudentResponse;
import reactor.core.publisher.Mono;

public interface PresenceInDormitoryPayload {
    Mono<String> getCode();

    Mono<LookupEstablishmentSectionClassStudentResponse> verifyESCSCode(String code);

}