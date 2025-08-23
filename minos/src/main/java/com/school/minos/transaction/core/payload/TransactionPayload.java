package com.school.minos.transaction.core.payload;

import reactor.core.publisher.Mono;

public interface TransactionPayload {
    Mono<String> getCode();

    Mono<Boolean> isTransactionCodeExist(String transactionCode);

    Mono<Double> sumAmountByESCS(String escs);
}
