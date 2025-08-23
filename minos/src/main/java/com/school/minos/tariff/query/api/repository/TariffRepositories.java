package com.school.minos.tariff.query.api.repository;

import com.school.minos.tariff.core.model.Tariff;
import reactor.core.publisher.Mono;

public
interface TariffRepositories {
    Mono<Tariff> getLastTariff ( );
}
