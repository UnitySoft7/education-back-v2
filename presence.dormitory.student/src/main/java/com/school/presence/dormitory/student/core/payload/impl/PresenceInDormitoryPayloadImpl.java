package com.school.presence.dormitory.student.core.payload.impl;

import com.school.presence.dormitory.student.core.common.PresenceInDormitoryCode;
import com.school.presence.dormitory.student.core.payload.PresenceInDormitoryPayload;
import com.school.presence.dormitory.student.query.api.dto.LookupEstablishmentSectionClassStudentResponse;
import com.school.presence.dormitory.student.query.api.query.FindByCodeQuery;
import com.school.presence.dormitory.student.query.api.repository.PresenceInDormitoryRepositories;
import com.school.presence.dormitory.student.query.api.repository.PresenceInDormitoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Component
@RequiredArgsConstructor
public class PresenceInDormitoryPayloadImpl implements PresenceInDormitoryPayload {
    private final PresenceInDormitoryRepository   presenceInDormitoryRepository;
    private final PresenceInDormitoryRepositories presenceInDormitoryRepositories;


    @Override
    public
    Mono<String> getCode(){
        return presenceInDormitoryRepository.count().flatMap( aLong -> {
            if (aLong == 0) { return Mono.just("PDS100000000001");   }
            else { Mono<String> code = presenceInDormitoryRepositories.getLastPresenceInDormitory()  .flatMap(value -> Mono.just(value.getPresenceInDormitoryCode())); return PresenceInDormitoryCode.generate(code);  }
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
