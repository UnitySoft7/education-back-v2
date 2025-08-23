package com.school.examination.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Course teacher response")
public record CourseTeacherResponse(
        String establishmentSectionClassId, String establishmentSectionClassCourseCode,
        String establishmentSectionClassCode, String establishmentSectionClassName,
        String establishmentCode, String establishmentName, String sectionCode,
        String sectionName, String classCode, String className, String courseCode,
        String courseName, String teacherCode, String teacherName, String logCreatedAt,
        String logCreatedMonth, String logCreatedYear, String logCreatedDate) implements Serializable {}
