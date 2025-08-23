package com.school.classroom.core.model;

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
@Document(collection = "classroom")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Classroom response")
public class Classroom {
    @Id
    private String classroomId;
    private String name;
    private String code;
    private String frName;
    private String enName;
    private String sectionName;
    private String sectionCode;
    private String establishmentName;
    private String establishmentCode;
}
