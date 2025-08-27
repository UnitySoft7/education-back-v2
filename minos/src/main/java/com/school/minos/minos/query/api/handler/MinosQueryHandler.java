package com.school.minos.minos.query.api.handler;

import com.school.minos.minos.core.model.Minos;
import com.school.minos.minos.query.api.response.MinosResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public
interface MinosQueryHandler {
    Flux<MinosResponse> findMinos();

    Mono<MinosResponse> findMinosByMinosCode(String clothCode);

    MinosResponse getMinos(Minos minos);
}