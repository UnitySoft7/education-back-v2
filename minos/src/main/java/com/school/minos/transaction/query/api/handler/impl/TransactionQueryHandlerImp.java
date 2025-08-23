package com.school.minos.transaction.query.api.handler.impl;

import com.school.minos.transaction.core.model.Transaction;
import com.school.minos.transaction.query.api.handler.TransactionQueryHandler;
import com.school.minos.transaction.query.api.query.TransactionByStudentQuery;
import com.school.minos.transaction.query.api.repository.TransactionRepository;
import com.school.minos.transaction.query.api.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TransactionQueryHandlerImp implements TransactionQueryHandler {
    private final TransactionRepository transactionRepository;

    @Override
    public Flux<TransactionResponse> findTransactions() {
        return transactionRepository.findAll().map(this::getTransaction);
    }

    @Override
    public Mono<TransactionResponse> findTransactionByCode(String code) {
        return transactionRepository.findTransactionByTransaCode(code).map(this::getTransaction);
    }

    @Override
    public Flux<TransactionResponse> findTransactionByStudent(String code) {
        return transactionRepository.findTransactionByStudentCode(code).map(this::getTransaction);
    }

    @Override
    public Flux<TransactionResponse> findTransactionByStudentAndSemester(TransactionByStudentQuery query) {
        return transactionRepository.findTransactionByStudentCodeAndTrimester(
                query.studentCode(), query.trimester()).map(this::getTransaction);
    }

    public TransactionResponse getTransaction(Transaction transaction) {
        return new TransactionResponse(transaction.getTransaId(),
                transaction.getTransaCode(), transaction.getMinosCode(),
                transaction.getStudentCode(), transaction.getStudentFullname(),
                transaction.getTrimester(), transaction.getAmount(), transaction.getMaxamount(),
                transaction.getStatus(), transaction.getSchoolYear(),
                transaction.getEstablishmentName(), transaction.getEstablishmentCode(),
                transaction.getSectionName(), transaction.getSectionCode(),
                transaction.getClassroomName(), transaction.getClassroomCode(),
                transaction.getLogCreatedAt(), transaction.getLogCreatedDate(),
                transaction.getLogCreatedMonth(), transaction.getLogCreatedYear());
    }
}
