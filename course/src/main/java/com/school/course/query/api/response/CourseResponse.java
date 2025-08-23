package com.school.course.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Course Response")
public record CourseResponse(
        String CourseId,
        String courseName,
        String courseCode,
        String frName,
        String enName,
        String establishmentName,
        String establishmentCode) implements Serializable {}