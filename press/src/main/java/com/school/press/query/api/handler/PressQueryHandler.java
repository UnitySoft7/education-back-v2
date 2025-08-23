package com.school.press.query.api.handler;

import com.school.press.core.model.Press;
import com.school.press.query.api.response.PressResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PressQueryHandler {


    Flux<PressResponse> findPresss();

    Mono<PressResponse> findPressById(String clothId);

    Mono<PressResponse> findPressByPressCode(String clothCode);

    PressResponse getPress(Press Press);
}