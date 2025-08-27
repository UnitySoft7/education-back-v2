package com.school.minos.transaction.query.api.repository;

import com.school.minos.transaction.core.model.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {
    Flux<Transaction> findTransactionByStudentCode(@Param("studentCode") String studentCode);

    Flux<Transaction> findTransactionByStudentCodeAndTrimester(
            @Param("studentCode") String studentCode, @Param("trimester") String trimester);
}
