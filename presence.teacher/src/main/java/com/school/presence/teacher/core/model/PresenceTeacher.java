package com.school.presence.teacher.core.model;



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
@Document(collection = "presenceTeacher")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "PresenceTeacher response")
public class PresenceTeacher {
    @Id
    private String presenceTeacherId;
    private String code;
    private String prof;
    private String profName;
    private String pointer;
    private String pointerName;
    private String departHour;
    private String day;
    private String trimester;
    private String schoolYear;
    private String logCreatedAt;
    private String logCreatedDate;
    private String logCreatedMonth;
    private String logCreatedYear;



}
