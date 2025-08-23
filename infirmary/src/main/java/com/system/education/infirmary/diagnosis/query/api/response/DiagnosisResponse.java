package com.system.education.infirmary.diagnosis.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Diagnosis response")
public record DiagnosisResponse(
        String diagnosisId,
        String diagnosisCode,
        String studentCode,
        String studentName,
        String condition,
        String comment,
        String establishmentCode,
        String establishmentName,
        String semester,
        String schoolYear,
        String logCreatedAt,
        String logCreatedDate,
        String logCreatedMonth,
        String logCreatedYear) implements Serializable {}
