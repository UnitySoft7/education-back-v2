package com.school.bed.query.api.repository.impl;

import com.school.bed.core.model.Bed;
import com.school.bed.query.api.repository.BedRepositories;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class BedRepositoriesImpl implements BedRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public BedRepositoriesImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }
    @Override
    public
    Mono<Bed> getLastBed ( ) {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "code"));
        query.limit(1);
        return reactiveMongoTemplate.find(query, Bed.class).next();}

}



