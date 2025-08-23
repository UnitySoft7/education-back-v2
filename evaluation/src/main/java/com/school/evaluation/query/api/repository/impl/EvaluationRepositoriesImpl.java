package com.school.evaluation.query.api.repository.impl;

import com.school.evaluation.core.model.Evaluation;
import com.school.evaluation.query.api.repository.EvaluationRepositories;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class EvaluationRepositoriesImpl implements EvaluationRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public EvaluationRepositoriesImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }
    @Override
    public Mono<Evaluation> getLastEvaluation ( ) {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "code"));
        query.limit(1);
        return reactiveMongoTemplate.find(query, Evaluation.class)
                .next();
    }
}
