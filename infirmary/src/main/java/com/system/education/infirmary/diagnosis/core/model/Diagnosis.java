package com.system.education.infirmary.diagnosis.core.model;

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
@Document(collection = "diagnosis")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Diagnosis response")
public class Diagnosis {
    @Id
    private String diagnosisId;
    private String diagnosisCode;
    private String studentCode;
    private String studentName;
    private String condition;
    private String comment;
    private String establishmentCode;
    private String establishmentName;
    private String semester;
    private String schoolYear;
    private String logCreatedAt;
    private String logCreatedDate;
    private String logCreatedMonth;
    private String logCreatedYear;
}
