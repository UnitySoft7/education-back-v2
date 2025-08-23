package com.school.course.query.api.dto;

import com.school.course.query.api.response.CourseResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
@Schema(name = "Lookup Course response")
public record LookupCourseResponse(boolean success, CourseResponse clothResponse) implements Serializable {}
