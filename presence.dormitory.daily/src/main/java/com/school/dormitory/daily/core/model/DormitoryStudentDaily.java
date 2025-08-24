package com.school.dormitory.daily.core.model;



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
@Document(collection = "dormitoryStudentDaily")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "DormitoryStudentDaily response")
public class DormitoryStudentDaily {
    @Id
    private String dormitoryStudentDailyId;
    private String code;
    private String dormitoryInClassCode;
    private String dormitory;
    private String student;
    private String presentStatus;
    private String prof;
    private  String scheduleCode;
    private double absents;
    private double presents;
    private String schoolYear;
    private String trimester;
    private String status;
    private String logCreatedAt;



}
