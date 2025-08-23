package com.school.minos.transaction.query.api.repository.impl;

import com.school.minos.transaction.core.model.Transaction;
import com.school.minos.transaction.query.api.repository.TransactionRepositories;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class TransactionRepositoriesImpl implements TransactionRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public TransactionRepositoriesImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }
    @Override
    public
    Mono<Transaction> getLastTransaction ( ) {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "transaCode"));
        query.limit(1);
        return reactiveMongoTemplate.find(query, Transaction.class)
                .next();
    }


}
