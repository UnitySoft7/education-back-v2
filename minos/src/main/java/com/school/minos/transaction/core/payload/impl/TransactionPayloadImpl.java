package com.school.minos.transaction.core.payload.impl;

import com.school.minos.core.common.TransactionCode;
import com.school.minos.transaction.core.model.Transaction;
import com.school.minos.transaction.core.payload.TransactionPayload;
import com.school.minos.transaction.query.api.repository.TransactionRepositories;
import com.school.minos.transaction.query.api.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TransactionPayloadImpl implements TransactionPayload {
    private final TransactionRepository transactionRepository;
    private final TransactionRepositories transactionRepositories;

    @Override
    public Mono<String> getCode() {
        return transactionRepository.count().flatMap(aLong -> {
            if (aLong == 0) {
                return Mono.just("TRS100000000001");
            } else {
                Mono<String> code = transactionRepositories.getLastTransaction().flatMap(value -> Mono.just(value.getTransaCode()));
                return TransactionCode.generate(code);
            }
        });
    }

    @Override
    public Mono<Boolean> isTransactionCodeExist(String transactionCode) {
        return transactionRepository.existsTransactionByTransaCode(transactionCode);
    }

    @Override
    public Mono<Double> sumAmountByESCS(String escs) {
        return transactionRepository.findByESCS(escs).map(Transaction::getAmount).reduce(0.0, Double::sum);
    }


}
