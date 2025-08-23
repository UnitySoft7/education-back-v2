package com.system.education.teacher.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Teacher updated function command")
public record TeacherUpdatedFunctionCommand(
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Invalid code")
        String teacherCode,
        String function) implements Serializable {}
