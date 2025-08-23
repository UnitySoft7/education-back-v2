package com.system.education.student.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "Student class response")
public record PresenceClassResponse(
        List<StudentClassResponse> studentClassResponse, String total) implements Serializable {}
