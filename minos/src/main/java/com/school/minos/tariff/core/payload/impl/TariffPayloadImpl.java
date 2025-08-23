package com.school.minos.tariff.core.payload.impl;

import com.school.minos.core.common.TariffCode;
import com.school.minos.tariff.core.payload.TariffPayload;
import com.school.minos.tariff.query.api.repository.TariffRepositories;
import com.school.minos.tariff.query.api.repository.TariffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TariffPayloadImpl implements TariffPayload {
    private final TariffRepository   minosRepository;
    private final TariffRepositories minosRepositories;

    @Override
    public Mono<String> getCode() {
        return minosRepository.count().flatMap(aLong -> {
            if (aLong == 0) {
                return Mono.just("ESTM100000000001");
            } else {
                Mono<String> code = minosRepositories.getLastTariff()
                        .flatMap(value -> Mono.just(value.getTariffCode()));
                return TariffCode.generate(code);
            }
        });
    }
}
