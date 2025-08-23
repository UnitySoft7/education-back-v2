package com.school.schedule.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Establishment Section Class Course response")
public record EstablishmentSectionClassCourseResponse(
        String establishmentSectionClassId, String establishmentSectionClassCourseCode,
        String establishmentSectionClassCode, String establishmentSectionClassName,
        String establishmentCode, String establishmentName, String sectionCode,
        String sectionName, String className, String classCode, String courseCode,
        String courseName, String logCreatedAt, String logCreatedMonth, String logCreatedYear,
        String logCreatedDate) implements Serializable {}
