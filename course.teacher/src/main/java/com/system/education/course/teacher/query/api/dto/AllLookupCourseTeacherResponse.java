package com.system.education.course.teacher.query.api.dto;

import com.system.education.course.teacher.query.api.response.CourseTeacherResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All lookup course teacher response")
public record AllLookupCourseTeacherResponse(
        boolean success, List<CourseTeacherResponse> courseTeacherResponses) implements Serializable {}
