package com.school.minos.transaction.query.api.dto;

import com.school.minos.transaction.query.api.response.TransactionResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup Transaction Response")
public record LookupTransactionResponse(boolean success, TransactionResponse transactionResponse) implements Serializable {}
