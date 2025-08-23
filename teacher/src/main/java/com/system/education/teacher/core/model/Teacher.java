package com.system.education.teacher.core.model;

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
@Document(collection = "teacher")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Teacher response")
public class Teacher {
    @Id
    private String teacherId;
    private String teacherCode;
    private String fullName;
    private String citizenId;
    private String phoneNo;
    private String matricule;
    private String address;
    private String gender;
    private String function;
    private String establishmentCode;
    private String establishmentName;
    private String logCreatedAt;
    private String logCreatedMonth;
    private String logCreatedYear;
    private String logCreatedDate;
}
