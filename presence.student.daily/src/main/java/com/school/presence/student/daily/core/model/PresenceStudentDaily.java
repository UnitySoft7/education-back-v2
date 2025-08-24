package com.school.presence.student.daily.core.model;



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
@Document(collection = "presenceStudentDaily")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "PresenceStudentDaily response")
public class PresenceStudentDaily {
    @Id
    private String presenceStudentDailyId;
    private String code;
    private String presenceInClassCode;
    private String presence;
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
