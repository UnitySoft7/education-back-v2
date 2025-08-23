package com.school.minos.tariff.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Tariff Response")
public record TariffResponse(
        String minosId,
        String minosCode,
        double amount,
        String establishmentName,
        String establishmentCode,
        String sectionName,
        String sectionCode,
        String classroomName,
        String classroomCode,
        String logCreatedAt,
        String logCreatedDate,
        String logCreatedMonth,
        String logCreatedYear) implements Serializable {}
