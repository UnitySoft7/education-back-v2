package com.system.education.establishment.query.api.repository;

import com.system.education.establishment.core.model.Establishment;
import reactor.core.publisher.Mono;

public interface EstablishmentRepositories {
    Mono<Establishment> getLastEstablishment();
}
