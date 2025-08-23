package com.school.evaluation.core.model;
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
@Document(collection = "evaluation")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Evaluation response")
public class Evaluation {
    @Id
    private String evaluationId;
    private String name;
    private String code;
    private String profFullname;
    private String profCode;
    private double noteMax;
//    private String ESCC;private String ESC;private String ESCCT;private String ESCS;
    private String courseName;
    private String courseCode;
    private String establishmentName;
    private String establishmentCode;
    private String sectionName;
    private String sectionCode;
    private String classroomName;
    private String classroomCode;
    private String trimester;
    private String schoolYear;
    private String logCreatedAt;
    private String logCreatedDate;
    private String logCreatedMonth;
    private String logCreatedYear;
}
