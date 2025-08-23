package com.school.minos.transaction.query.api.dto;

import com.school.minos.transaction.query.api.response.TransactionResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup Transaction Response")
public record AllLookupTransactionResponse(boolean success, List<TransactionResponse> transactionResponses) implements Serializable {}
