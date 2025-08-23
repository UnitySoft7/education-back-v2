package com.school.schedule.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Establishment Section Class response")
public record EstablishmentSectionClassResponse(
        String establishmentSectionClassId, String establishmentSectionClassCode,
        String establishmentSectionClassName, String establishmentSectionCode,
        String establishmentCode, String establishmentName, String sectionCode,
        String sectionName, String classCode, String className, String logCreatedAt,
        String logCreatedMonth, String logCreatedYear, String logCreatedDate) implements Serializable {}
