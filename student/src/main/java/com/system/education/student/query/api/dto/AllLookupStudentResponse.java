package com.system.education.student.query.api.dto;

import com.system.education.student.query.api.response.StudentResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All lookup student response")
public record AllLookupStudentResponse(
        boolean success, List<StudentResponse> studentResponses) implements Serializable {}
