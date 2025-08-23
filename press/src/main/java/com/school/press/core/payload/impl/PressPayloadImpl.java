package com.school.press.core.payload.impl;

import com.school.press.core.common.PressCode;
import com.school.press.core.payload.PressPayload;
import com.school.press.query.api.repository.PressRepositories;
import com.school.press.query.api.repository.PressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PressPayloadImpl implements PressPayload {
    private final PressRepository pressRepository;
    private final PressRepositories pressRepositories;

    @Override
    public Mono<String> getCode() {
        return pressRepository.count().flatMap(aLong -> {
            if (aLong == 0) {
                return Mono.just("PS100000000001");
            } else {
                Mono<String> code = pressRepositories.getLastPress().flatMap(value -> Mono.just(value.getCode()));
                return PressCode.generate(code);
            }
        });
    }

}
