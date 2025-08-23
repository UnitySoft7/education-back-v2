package com.school.bed.core.payload.impl;

import com.school.bed.core.common.BedCode;
import com.school.bed.core.payload.BedPayload;
import com.school.bed.query.api.repository.BedRepositories;
import com.school.bed.query.api.repository.BedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BedPayloadImpl implements BedPayload {
    private final BedRepository bedRepository;
    private final BedRepositories bedRepositories;
    @Override
    public Mono<String> getCode() {
        return bedRepository.count().flatMap(aLong -> {
            if (aLong == 0) {
                return Mono.just("BD100000000001");
            } else {
                Mono<String> code = bedRepositories.getLastBed().flatMap(value -> Mono.just(value.getCode()));
                return BedCode.generate(code);
            }
        });
    }

}
