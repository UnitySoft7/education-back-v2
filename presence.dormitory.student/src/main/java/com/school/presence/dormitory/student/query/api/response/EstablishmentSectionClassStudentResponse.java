package com.school.presence.dormitory.student.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Establishment Section Class Student response")
public record EstablishmentSectionClassStudentResponse(
        String establishmentSectionClassId, String establishmentSectionClassStudentCode,
        String establishmentSectionClassCode, String establishmentSectionClassName,
        String establishmentCode, String establishmentName, String sectionCode,
        String sectionName, String classCode, String className, String studentCode,
        String studentName, String logCreatedAt, String logCreatedMonth, String logCreatedYear,
        String logCreatedDate) implements Serializable {}
