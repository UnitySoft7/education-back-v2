package com.system.education.course.teacher.query.api.dto;

import com.system.education.course.teacher.query.api.response.CourseTeacherResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup course teacher response")
public record LookupCourseTeacherResponse(
        boolean success, CourseTeacherResponse courseTeacherResponse) implements Serializable {}
