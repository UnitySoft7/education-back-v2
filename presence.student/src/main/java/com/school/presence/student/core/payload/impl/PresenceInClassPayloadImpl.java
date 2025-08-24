package com.school.presence.student.core.payload.impl;

import com.school.presence.student.core.common.PresenceInClassCode;
import com.school.presence.student.core.payload.PresenceInClassPayload;
import com.school.presence.student.query.api.dto.LookupEstablishmentSectionClassStudentResponse;
import com.school.presence.student.query.api.query.FindByCodeQuery;
import com.school.presence.student.query.api.repository.PresenceInClassRepositories;
import com.school.presence.student.query.api.repository.PresenceInClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Component
@RequiredArgsConstructor
public class PresenceInClassPayloadImpl implements PresenceInClassPayload {
    private final PresenceInClassRepository   presenceInClassRepository;
    private final PresenceInClassRepositories presenceInClassRepositories;


    @Override
    public
    Mono<String> getCode(){
        return presenceInClassRepository.count().flatMap( aLong -> {
            if (aLong == 0) { return Mono.just("PIC100000000001");   }
            else { Mono<String> code = presenceInClassRepositories.getLastPresenceInClass()  .flatMap(value -> Mono.just(value.getPresenceInClassCode())); return PresenceInClassCode.generate(code);  }
        });
    }
    @Override
    public Mono<LookupEstablishmentSectionClassStudentResponse> verifyESCSCode(String code) {
        FindByCodeQuery query = new FindByCodeQuery(code);
        return WebClient.create().put().uri("http://127.0.0.1:9909/api/v1/education/establishment-section-class-student/establishment-section-class-student-lookup/get-establishment-section-class-student-by-establishment-section-class-student-code").bodyValue(query).retrieve().bodyToMono(LookupEstablishmentSectionClassStudentResponse.class);
    }

//    @Override
//    public Mono<LookupScheduleIndexResponse> verifyScheduleCodeForIndex1(String code, String index) {
//        FindByCodeAndIndexQuery query = new FindByCodeAndIndexQuery(code, index);
//        return WebClient.create().put().uri("http://127.0.0.1:9810/api/v1/education/schedule/lookup-schedule/get-schedule-by-schedule-code-and-index").bodyValue(query).retrieve().bodyToMono(LookupScheduleIndexResponse.class);
//    }
}
