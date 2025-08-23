package com.system.education.course.teacher.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Establishment Section Class Course response")
public record EstablishmentSectionClassCourseResponse(
        String establishmentSectionClassId, String establishmentSectionClassCourseCode,
        String establishmentSectionClassCode, String establishmentSectionClassName,
        String establishmentCode, String establishmentName, String sectionCode,
        String sectionName, String classCode, String className, String courseCode,
        String courseName, double ponderation, String logCreatedAt, String logCreatedMonth,
        String logCreatedYear, String logCreatedDate) implements Serializable {}
