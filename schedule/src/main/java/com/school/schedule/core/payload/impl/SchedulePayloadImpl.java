package com.school.schedule.core.payload.impl;

import com.school.schedule.cmd.api.query.FindByCodeQuery;
import com.school.schedule.core.common.ScheduleCode;
import com.school.schedule.core.payload.SchedulePayload;
import com.school.schedule.query.api.dto.LookupEstablishmentSectionClassCourseResponse;
import com.school.schedule.query.api.repository.ScheduleRepositories;
import com.school.schedule.query.api.repository.ScheduleRepository;
import com.school.schedule.query.api.response.LookupEstablishmentSectionClassResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SchedulePayloadImpl implements SchedulePayload {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleRepositories scheduleRepositories;

    @Override
    public Mono<String> getCode() {
        return scheduleRepository.count().flatMap(aLong -> {
            if (aLong == 0) {
                return Mono.just("SH100000000001");
            } else {
                Mono<String> code = scheduleRepositories.getLastSchedule().flatMap(value -> Mono.just(value.getCode()));
                return ScheduleCode.generate(code);
            }
        });
    }

    @Override
    public Mono<Boolean> isScheduleCodeExist(String ScheduleCode) {
        return scheduleRepository.existsScheduleByCode(ScheduleCode);
    }

    @Override
    public Mono<LookupEstablishmentSectionClassResponse> verifyECS(String code) {
        FindByCodeQuery query = new FindByCodeQuery(code);
        return WebClient.create().put().uri("http://127.0.0.1:9907/api/v1/education/establishment-section-class/establishment-section-class-lookup/get-establishment-section-class-by-establishment-section-class-code").bodyValue(query).retrieve().bodyToMono(LookupEstablishmentSectionClassResponse.class);
    }

    @Override
    public Mono<LookupEstablishmentSectionClassCourseResponse> verifyECSC(String code) {
        FindByCodeQuery query = new FindByCodeQuery(code);
        return WebClient.create().put().uri("http://127.0.0.1:9908/api/v1/education/establishment-section-class-course/establishment-section-class-course-lookup/get-establishment-section-class-course-by-establishment-section-class-course-code").bodyValue(query).retrieve().bodyToMono(LookupEstablishmentSectionClassCourseResponse.class);
    }

}
