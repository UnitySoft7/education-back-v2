package com.school.minos.tariff.query.api.handler;

import com.school.minos.tariff.query.api.response.TariffResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public
interface TariffQueryHandler {
    Flux<TariffResponse> findTariffs();
    Mono<TariffResponse> findTariffByTariffCode(String tariffCode);

    Mono<TariffResponse> findTariffByTariffId(String tariffId);

    Mono<TariffResponse> findTariffByClassCode(String classCode);
    Flux<TariffResponse> findTariffByEstablishment(String code);
}