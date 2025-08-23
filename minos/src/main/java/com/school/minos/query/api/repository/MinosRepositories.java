package com.school.minos.query.api.repository;

import com.school.minos.core.model.Minos;
import reactor.core.publisher.Mono;

public
interface MinosRepositories {
    Mono<Minos> getLastMinos ( );
}
