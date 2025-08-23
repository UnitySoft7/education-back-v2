package com.school.press.query.api.repository;

import com.school.press.core.model.Press;
import reactor.core.publisher.Mono;

public interface PressRepositories {
    Mono<Press> getLastPress();
}
