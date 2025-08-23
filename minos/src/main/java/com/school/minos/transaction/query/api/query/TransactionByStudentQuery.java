package com.school.minos.transaction.query.api.query;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Transaction by student query")
public record TransactionByStudentQuery(String studentCode, String trimester) implements Serializable {}
