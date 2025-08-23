package com.school.minos.transaction.query.api.repository;

import com.school.minos.transaction.core.model.Transaction;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {

    Mono<Boolean> existsTransactionByTransaCode(@Param("transaCode") String transaCode);

    Mono<Transaction> findTransactionByTransaCode(@Param("transaCode") String transaCode);

    Flux<Transaction> findTransactionByStudentCode(@Param("studentCode") String studentCode);

    Flux<Transaction> findTransactionByStudentCodeAndTrimester(@Param("studentCode") String studentCode, @Param("trimester") String trimester);

    @Query("{ 'ESCS': ?0 }")
    Flux<Transaction> findByESCS(String escs);
}
