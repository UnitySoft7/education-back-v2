package com.school.dormitory.core.payload.impl;
import com.school.dormitory.core.common.DormitoryCode;
import com.school.dormitory.core.payload.DormitoryPayload;
import com.school.dormitory.query.api.repository.DormitoryRepositories;
import com.school.dormitory.query.api.repository.DormitoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
@Component
@RequiredArgsConstructor
public class DormitoryPayloadImpl implements DormitoryPayload {
    private final DormitoryRepository   dormitoryRepository;
    private final DormitoryRepositories dormitoryRepositories;


    @Override
    public
    Mono<String> getCode(){
        return dormitoryRepository.count().flatMap( aLong -> {
            if (aLong == 0) {
                return Mono.just("DM100000000001"); }
            else {  Mono<String> code = dormitoryRepositories.getLastDormitory()
                        .flatMap(value -> Mono.just(value.getCode ()));
                return DormitoryCode.generate(code);
            }
        });    }

    @Override
    public
    Mono<Boolean> isDormitoryNameExist(String name) {
        return dormitoryRepository.existsDormitoryByName (name);
    }

    @Override
    public
    Mono<Boolean> isDormitoryCodeExist(String DormitoryCode) {
        return dormitoryRepository.existsDormitoryByCode (DormitoryCode);
    }

}
