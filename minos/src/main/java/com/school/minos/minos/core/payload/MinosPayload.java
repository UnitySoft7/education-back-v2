package com.school.minos.minos.core.payload;

import reactor.core.publisher.Mono;

public
interface MinosPayload {
    Mono<String> getCode ( );

    Mono<Boolean> isMinosCodeExist ( String minosCode );
}