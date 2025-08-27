package com.school.minos.transaction.query.api.handler;

import com.school.minos.transaction.query.api.query.TransactionByStudentQuery;
import com.school.minos.transaction.query.api.response.TransactionResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionQueryHandler {
    Flux<TransactionResponse> findTransactions();
    Flux<TransactionResponse> findTransactionByStudent(String code);
    Flux<TransactionResponse> findTransactionByStudentAndSemester(TransactionByStudentQuery query);
}
