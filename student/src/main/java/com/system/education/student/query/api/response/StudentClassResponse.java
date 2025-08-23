package com.system.education.student.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Student class response")
public record StudentClassResponse(
        String studentCode, String fullName) implements Serializable {}
