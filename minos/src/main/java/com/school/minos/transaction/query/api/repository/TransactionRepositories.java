package com.school.minos.transaction.query.api.repository;

import com.school.minos.transaction.core.model.Transaction;
import reactor.core.publisher.Mono;

public
interface TransactionRepositories {
    Mono<Transaction> getLastTransaction ( );
}
