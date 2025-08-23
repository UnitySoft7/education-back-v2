package com.system.education.cafeteria.consumed.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Consumed response")
public record ConsumedResponse(
        String consumedId,
        String productCode,
        String productName,
        String qty,
        double amount,
        String studentCode,
        String studentName,
        String employeeCode,
        String employeeName,
        String establishmentCode,
        String establishmentName,
        String semester,
        String schoolYear,
        String status,
        String logCreatedAt,
        String logCreatedDate,
        String logCreatedMonth,
        String logCreatedYear) implements Serializable {}
