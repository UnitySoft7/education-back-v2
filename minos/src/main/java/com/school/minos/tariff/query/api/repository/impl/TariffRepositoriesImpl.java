package com.school.minos.tariff.query.api.repository.impl;

import com.school.minos.tariff.core.model.Tariff;
import com.school.minos.tariff.query.api.repository.TariffRepositories;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class TariffRepositoriesImpl implements TariffRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public TariffRepositoriesImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }
    @Override
    public
    Mono<Tariff> getLastTariff ( ) {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "tariffCode"));
        query.limit(1);
        return reactiveMongoTemplate.find(query, Tariff.class)
                .next();
    }
}
