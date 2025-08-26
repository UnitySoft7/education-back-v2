package com.school.evaluate.core.model;

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
@Document(collection = "evaluate")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Evaluate response")
public class Evaluate {
    @Id
    private String evaluateId;
    private String code;
    private String evaluationName;
    private String evaluationCode;
    private String profFullname;
    private String profCode;
    private String courseName;
    private String courseCode;
    private String studentCode;
    private String studentFullname;
    private double noteMax;
    private double note;
    private String establishmentName;
    private String establishmentCode;
    private String sectionName;
    private String sectionCode;
    private String classroomName;
    private String classroomCode;
    private String trimester;
    private String schoolYear;
    private String comment;

}
