package com.system.education.teacher.query.api.dto;

import com.system.education.teacher.query.api.response.TeacherResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup teacher response")
public record LookupTeacherResponse(
        boolean success, TeacherResponse teacherResponse) implements Serializable {}
