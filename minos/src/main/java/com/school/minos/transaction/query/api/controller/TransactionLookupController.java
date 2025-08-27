package com.school.minos.transaction.query.api.controller;

import com.school.minos.transaction.query.api.dto.AllLookupTransactionResponse;
import com.school.minos.transaction.query.api.handler.TransactionQueryHandler;
import com.school.minos.transaction.query.api.query.TransactionByStudentQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/transaction/transaction-lookup/")
@Tag(name = "Transaction", description = "Data REST API for class resource")
public class TransactionLookupController {
    private final TransactionQueryHandler transactionQueryHandler;

    @Operation(summary = "Retrieve data transaction")
    @GetMapping(path = "get-transaction", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupTransactionResponse>> show() {
        return transactionQueryHandler.findTransactions()
                .collectList()
                .map(transaction ->
                        new AllLookupTransactionResponse(true, transaction))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data transaction by studentCode")
    @GetMapping(path = "get-transaction-by-student-code/{studentCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByStudentCode(@PathVariable("studentCode") String studentCode) {
        return transactionQueryHandler.findTransactionByStudent(studentCode)
                .collectList()
                .map(transactionResponses ->
                        new AllLookupTransactionResponse(true, transactionResponses))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data transaction by student and semester")
    @PutMapping(path = "get-transaction-by-student-semester", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByStudentAndSemester(@Valid @RequestBody TransactionByStudentQuery query) {
        return transactionQueryHandler.findTransactionByStudentAndSemester(query)
                .collectList()
                .map(transactionResponses ->
                        new AllLookupTransactionResponse(true, transactionResponses))
                .map(ResponseEntity::ok);
    }
}
