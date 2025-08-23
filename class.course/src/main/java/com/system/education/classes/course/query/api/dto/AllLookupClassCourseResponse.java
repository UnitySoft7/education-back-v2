package com.system.education.classes.course.query.api.dto;

import com.system.education.classes.course.query.api.response.ClassCourseResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All lookup class course response")
public record AllLookupClassCourseResponse(
        boolean success, List<ClassCourseResponse> classCourseResponses) implements Serializable {}
