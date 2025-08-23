package com.school.examination.query.api.dto;

import com.school.examination.query.api.response.CourseTeacherResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup course teacher response")
public record LookupCourseTeacherResponse(
        boolean success, CourseTeacherResponse courseTeacherResponse) implements Serializable {}
