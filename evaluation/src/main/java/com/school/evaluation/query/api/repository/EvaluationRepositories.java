package com.school.evaluation.query.api.repository;

import com.school.evaluation.core.model.Evaluation;
import reactor.core.publisher.Mono;

public
interface EvaluationRepositories {
    Mono<Evaluation> getLastEvaluation ( );
}
