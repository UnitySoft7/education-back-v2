package com.school.press.query.api.repository.impl;

import com.school.press.core.model.Press;
import com.school.press.query.api.repository.PressRepositories;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class PressRepositoriesImpl implements PressRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public PressRepositoriesImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }
    @Override
    public
    Mono<Press> getLastPress ( ) {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "code"));
        query.limit(1);
        return reactiveMongoTemplate.find(query, Press.class).next();}

}



