package com.school.evaluate.query.api.repository;

import com.school.evaluate.core.model.Evaluate;
import reactor.core.publisher.Mono;

public
interface EvaluateRepositories {
    Mono<Evaluate> getLastEvaluate ( );
}
