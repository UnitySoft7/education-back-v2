package com.school.dormitory.daily.core.payload.impl;
import com.school.dormitory.daily.core.common.DormitoryStudentDailyCode;
import com.school.dormitory.daily.core.payload.DormitoryStudentDailyPayload;
import com.school.dormitory.daily.query.api.repository.DormitoryStudentDailyRepositories;
import com.school.dormitory.daily.query.api.repository.DormitoryStudentDailyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
@Component
@RequiredArgsConstructor
public class DormitoryStudentDailyPayloadImpl implements DormitoryStudentDailyPayload {
    private final DormitoryStudentDailyRepository   dormitoryStudentDailyRepository;
    private final DormitoryStudentDailyRepositories dormitoryStudentDailyRepositories;

    @Override
    public
    Mono<String> getCode(){
        return dormitoryStudentDailyRepository.count().flatMap( aLong -> {
            if (aLong == 0) {
                return Mono.just("PDC100000000001"); }
            else {  Mono<String> code = dormitoryStudentDailyRepositories.getLastDormitoryStudentDaily()
                        .flatMap(value -> Mono.just(value.getCode ()));
                return DormitoryStudentDailyCode.generate(code);
            }
        });    }




}
