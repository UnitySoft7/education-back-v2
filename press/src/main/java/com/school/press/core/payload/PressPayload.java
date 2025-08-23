package com.school.press.core.payload;


import reactor.core.publisher.Mono;

public interface PressPayload {
    Mono<String> getCode();


}