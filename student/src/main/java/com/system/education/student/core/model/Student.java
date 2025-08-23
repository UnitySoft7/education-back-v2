package com.system.education.student.core.model;

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
@Document(collection = "student")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Student response")
public class Student {
    @Id
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String studentId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String studentCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String fullName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String birthday;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String gender;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String address;
    private String fatherName;
    private String fatherMobileNo;
    private String motherName;
    private String motherMobileNo;
    private String parentCode;
    private String parentName;
    private String profilePath;
    private String extractPath;
    private String bulletinPath;
    private String schoolYear;
    private String establishmentCode;
    private String establishmentName;
    private String sectionCode;
    private String sectionName;
    private String classCode;
    private String className;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String logCreatedAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String logCreatedMonth;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String logCreatedYear;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String logCreatedDate;
}
