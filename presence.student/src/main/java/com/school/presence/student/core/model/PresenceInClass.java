package com.school.presence.student.core.model;



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
@Document(collection = "presenceInClass")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "PresenceInClass response")
public class PresenceInClass {
    @Id
    private String presenceInClassId;
    private String presenceInClassCode;
    private String presence;
    private String pointer;
    private  String scheduleCode;
    private double effective;
    private double absents;
    private double presents;
    private String schoolYear;
    private String trimester;
    private String status;
    private String logCreatedAt;
    private String logCreatedDate;
    private String logCreatedMonth;
    private String logCreatedYear;
}









//    private String ESCC
//encadreur,prof