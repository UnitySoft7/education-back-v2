package com.system.education.establishment.query.api.repository.impl;

import com.system.education.establishment.core.model.Establishment;
import com.system.education.establishment.query.api.repository.EstablishmentRepositories;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class EstablishmentRepositoriesImpl implements EstablishmentRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public EstablishmentRepositoriesImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<Establishment> getLastEstablishment() {
        Query query = new Query();
//        query.addCriteria(Criteria.where("id").exists(true));
        query.with(Sort.by(Sort.Direction.DESC, "establishmentCode"));
        query.limit(1);

        return reactiveMongoTemplate.find(query, Establishment.class)
                .next();
    }
}
