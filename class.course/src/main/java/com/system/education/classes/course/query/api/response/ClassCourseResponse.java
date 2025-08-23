package com.system.education.classes.course.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Class Course response")
public record ClassCourseResponse(
        String classCourseId, String classCourseCode, String establishmentCode,
        String establishmentName, String sectionCode, String sectionName,
        String classCode, String className, String courseCode, String courseName,
        double ponderation, String logCreatedAt, String logCreatedMonth,
        String logCreatedYear, String logCreatedDate) implements Serializable {}
