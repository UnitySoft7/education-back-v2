package com.school.evaluate.query.api.repository.impl;

import com.school.evaluate.core.model.Evaluate;
import com.school.evaluate.query.api.repository.EvaluateRepositories;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class EvaluateRepositoriesImpl implements EvaluateRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public EvaluateRepositoriesImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }
    @Override
    public
    Mono<Evaluate> getLastEvaluate ( ) {
        Query query = new Query();
//        query.addCriteria(Criteria.where("clothCode").exists(true));
        query.with(Sort.by(Sort.Direction.DESC, "year"));
        query.limit(1);
        return reactiveMongoTemplate.find(query, Evaluate.class)
                .next();
    }
}
