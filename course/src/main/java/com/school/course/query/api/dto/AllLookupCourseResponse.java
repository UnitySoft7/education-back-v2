package com.school.course.query.api.dto;

import com.school.course.query.api.response.CourseResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup Course Response")
public record AllLookupCourseResponse(boolean success, List<CourseResponse> CourseResponses) implements Serializable {}
