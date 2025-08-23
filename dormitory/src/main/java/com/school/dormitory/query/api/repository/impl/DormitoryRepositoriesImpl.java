package com.school.dormitory.query.api.repository.impl;

import com.school.dormitory.core.model.Dormitory;
import com.school.dormitory.query.api.repository.DormitoryRepositories;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class DormitoryRepositoriesImpl implements DormitoryRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public DormitoryRepositoriesImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }
    @Override
    public
    Mono<Dormitory> getLastDormitory ( ) {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "code"));
        query.limit(1);
        return reactiveMongoTemplate.find(query, Dormitory.class).next();}

}



