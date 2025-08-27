package com.school.minos.minos.query.api.repository;

import com.school.minos.minos.core.model.Minos;
import reactor.core.publisher.Mono;

public
interface MinosRepositories {
    Mono<Minos> getLastMinos ( );
}
