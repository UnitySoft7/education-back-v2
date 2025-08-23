package com.school.minos.core.payload;

import reactor.core.publisher.Mono;

public
interface MinosPayload {
    Mono<String> getCode ( );

    Mono<Boolean> isMinosCodeExist ( String minosCode );
}