package com.system.education.course.teacher.core.model;

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
@Document(collection = "courseTeacher")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Course Teacher response")
public class CourseTeacher {
    @Id
    private String courseTeacherId;
    private String courseTeacherCode;
    private String establishmentCode;
    private String establishmentName;
    private String sectionCode;
    private String sectionName;
    private String classCode;
    private String className;
    private String courseCode;
    private String courseName;
    private String teacherCode;
    private String teacherName;
    private String logCreatedAt;
    private String logCreatedMonth;
    private String logCreatedYear;
    private String logCreatedDate;
}
