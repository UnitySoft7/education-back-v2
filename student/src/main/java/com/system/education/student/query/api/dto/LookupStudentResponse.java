package com.system.education.student.query.api.dto;

import com.system.education.student.query.api.response.StudentResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup student response")
public record LookupStudentResponse(
        boolean success, StudentResponse studentResponse) implements Serializable {}
