package com.school.presence.student.daily.core.payload.impl;
import com.school.presence.student.daily.core.common.PresenceStudentDailyCode;
import com.school.presence.student.daily.core.payload.PresenceStudentDailyPayload;
import com.school.presence.student.daily.query.api.repository.PresenceStudentDailyRepositories;
import com.school.presence.student.daily.query.api.repository.PresenceStudentDailyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
@Component
@RequiredArgsConstructor
public class PresenceStudentDailyPayloadImpl implements PresenceStudentDailyPayload {
    private final PresenceStudentDailyRepository   presenceStudentDailyRepository;
    private final PresenceStudentDailyRepositories presenceStudentDailyRepositories;


    @Override
    public
    Mono<String> getCode(){
        return presenceStudentDailyRepository.count().flatMap( aLong -> {
            if (aLong == 0) {
                return Mono.just("PSD100000000001"); }
            else {  Mono<String> code = presenceStudentDailyRepositories.getLastPresenceStudentDaily()
                        .flatMap(value -> Mono.just(value.getCode ()));
                return PresenceStudentDailyCode.generate(code);
            }
        });    }




}
