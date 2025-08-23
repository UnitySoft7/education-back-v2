package com.school.exam.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Exam Response")
public record ExamResponse(
        String examId,
        String name,
        String code,
        String profFullname,
        String profCode,
        double noteMax,
        String courseName,
        String courseCode,

        String establishmentName,
        String establishmentCode,
        String sectionName,
        String sectionCode,
        String classroomName,
        String classroomCode,
        String trimester,
        String schoolYear,
        String logCreatedAt,
        String logCreatedDate,
        String logCreatedMonth,
        String logCreatedYear
) implements Serializable {}
