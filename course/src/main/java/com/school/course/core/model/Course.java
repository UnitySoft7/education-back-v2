package com.school.course.core.model;

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
@Document(collection = "course")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Course response")
public class Course {
    @Id
    private String courseId;
    private String name;
    private String code;
    private String frName;
    private String enName;
    private String establishmentName;
    private String establishmentCode;
}
