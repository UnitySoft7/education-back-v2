package com.school.minos.minos.query.api.repository.impl;

import com.school.minos.minos.core.model.Minos;
import com.school.minos.minos.query.api.repository.MinosRepositories;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class MinosRepositoriesImpl implements MinosRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public MinosRepositoriesImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }
    @Override
    public
    Mono<Minos> getLastMinos ( ) {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "minosCode"));
        query.limit(1);
        return reactiveMongoTemplate.find(query, Minos.class)
                .next();
    }
}
