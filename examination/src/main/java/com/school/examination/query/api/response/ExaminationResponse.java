package com.school.examination.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Examination Response")
public record ExaminationResponse(
        String examinationId, String code, String examName, String examCode, String couseName,
        String courseCode, String studentCode, String studentFullname, String profFullname,
        String profCode, double noteMax, double note,double ponderation,double purcentage,
//        String ESCC, String ESCCT, String ESCS,
        String establishmentName, String establishmentCode, String sectionName,
        String sectionCode, String classroomName, String classroomCode, String trimester,
        String schoolYear,String commenrt) implements Serializable {
}
