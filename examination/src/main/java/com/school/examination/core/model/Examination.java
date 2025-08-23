package com.school.examination.core.model;
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
@Document(collection = "examination")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Examination response")
public class Examination {
    @Id
    private String examinationId;
    private String code;
    private String examName;
    private String examCode;
    private String studentCode;
    private String studentFullname;
    private String profFullname;
    private String profCode;
    private double noteMax;
    private  double note;
//    private String ESCC;
//    private String ESCCT;
//    private String ESCS;
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
    private String comment;

}
