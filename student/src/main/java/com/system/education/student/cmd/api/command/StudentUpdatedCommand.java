package com.system.education.student.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Student updated command")
public record StudentUpdatedCommand(
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Invalid student code")
        String studentCode,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z]{3,5}", message = "The gender must be between 3 and 5 characters")
        String gender,
        @NotNull(message = "The field is required")
        String address,
        String schoolYear,
        String sectionCode,
        String sectionName,
        String classCode,
        String className) implements Serializable {}
