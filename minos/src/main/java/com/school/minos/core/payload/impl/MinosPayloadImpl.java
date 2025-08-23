package com.school.minos.core.payload.impl;

import com.school.minos.core.common.MinosCode;
import com.school.minos.core.payload.MinosPayload;
import com.school.minos.query.api.repository.MinosRepositories;
import com.school.minos.query.api.repository.MinosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class MinosPayloadImpl implements MinosPayload {
    private final MinosRepository   minosRepository;
    private final MinosRepositories minosRepositories;
    @Override
    public
    Mono<String> getCode ( ){
        return minosRepository.count().flatMap(aLong -> {
            if (aLong == 0) {
                return Mono.just("ML100000000001");
            }
            else {
                Mono<String> code = minosRepositories.getLastMinos()
                        .flatMap(value -> Mono.just(value.getMinosCode ()));
                return MinosCode.generate(code);
            }
        });
    }
    @Override
    public
    Mono<Boolean> isMinosCodeExist ( String minosCode ) {
        return minosRepository.existsMinosByMinosCode(minosCode);
    }

//    @Override
//    public
//    Mono<Minos> create (Minos minos){
//        return minosRepository.findMinosByMinosCode(minos.getMinosCode());
//
//    }

}
