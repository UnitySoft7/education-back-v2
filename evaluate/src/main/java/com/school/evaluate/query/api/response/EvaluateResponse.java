package com.school.evaluate.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Evaluate Response")
public record EvaluateResponse(
        String evaluateId,
//        String name,
        String code,
        String evaluationName,
        String evaluationCode,
        String profFullname,
        String profCode,
        String courseName,
        String courseCode,
        String studentCode,
        String studentFullname,
        double ponderation,
        double noteMax,
        double note,
//        String ESCC,
//        String ESCCT,
//        String ESCS,
        String establishmentName,
        String establishmentCode,
        String sectionCode,
        String sectionName,
        String classroomName,
        String classroomCode,String trimester,
        String schoolYear,
        String commrent
) implements Serializable {}
