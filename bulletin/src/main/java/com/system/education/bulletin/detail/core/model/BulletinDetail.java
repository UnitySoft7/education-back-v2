package com.system.education.bulletin.detail.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "bulletinDetail")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Bulletin detail response")
public class BulletinDetail {
    @Id
    private String bulletinDetailId;
    private String studentCode;
    private String studentName;
    private String courseCode;
    private String courseName;
    private String midTermAssessment;
    private String examination;
    private String average;
    private String grade;
    private String teacherComment;
    private String teacherCode;
    private String teacherName;
    private String semester;
    private String schoolYear;
    private String classCode;
    private String className;
    private String establishmentCode;
    private String establishmentName;
    private String logCreatedAt;
    private String logCreatedMonth;
    private String logCreatedYear;
    private String logCreatedDate;
}
