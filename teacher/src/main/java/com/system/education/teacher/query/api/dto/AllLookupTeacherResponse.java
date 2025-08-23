package com.system.education.teacher.query.api.dto;

import com.system.education.teacher.query.api.response.TeacherResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All lookup teacher response")
public record AllLookupTeacherResponse(
        boolean success, List<TeacherResponse> teacherResponses) implements Serializable {}
