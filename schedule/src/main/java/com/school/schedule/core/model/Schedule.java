package com.school.schedule.core.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.school.schedule.query.api.response.DaysResponse;
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
@Document(collection = "schedule")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Schedule response")
public class Schedule {
    @Id
    private String scheduleId;
    private String code;

    private String index;
    private String establishmentName;
    private String establishmentCode;
    private String sectionName;
    private String sectionCode;
    private String classroomName;
    private String classroomCode;

    private DaysResponse monday;
    private DaysResponse tuesday;
    private DaysResponse wednesday;
    private DaysResponse thusday;
    private DaysResponse friday;
    private DaysResponse saturday;
    private DaysResponse sunday;

}
