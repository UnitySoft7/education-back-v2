package com.school.presence.dormitory.student.core.model;



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
@Document(collection = "presenceInDormitory")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "PresenceInDormitory response")
public class PresenceInDormitory {
    @Id
    private String presenceInDormitoryId;
    private String presenceInDormitoryCode;
    private String domitoryCode;
    private String presence;
    private String prof;
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