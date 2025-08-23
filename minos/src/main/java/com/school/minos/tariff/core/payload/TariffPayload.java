package com.school.minos.tariff.core.payload;

import reactor.core.publisher.Mono;

public
interface TariffPayload {
    Mono<String> getCode ( );
}