package com.school.bed.core.payload;


import reactor.core.publisher.Mono;

public
interface BedPayload {
    Mono<String> getCode();


}