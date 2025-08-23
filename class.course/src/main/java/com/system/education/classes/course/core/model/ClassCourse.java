package com.system.education.classes.course.core.model;

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
@Document(collection = "classCourse")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Class Course response")
public class ClassCourse {
    @Id
    private String classCourseId;
    private String classCourseCode;
    private String establishmentCode;
    private String establishmentName;
    private String sectionCode;
    private String sectionName;
    private String classCode;
    private String className;
    private String courseCode;
    private String courseName;
    private String ponderation;
    private String logCreatedAt;
    private String logCreatedMonth;
    private String logCreatedYear;
    private String logCreatedDate;
}
