package com.system.education.infirmary.consumption.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Consumption response")
public record ConsumptionResponse(
        String consumptionId,
        String studentCode,
        String studentName,
        double consumptionAmount,
        double payedAmount,
        String status,
        String establishmentCode,
        String establishmentName,
        String semester,
        String schoolYear,
        String logCreatedAt,
        String logCreatedDate,
        String logCreatedMonth,
        String logCreatedYear) implements Serializable {}
