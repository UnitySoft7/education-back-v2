package com.system.education.skill.detail.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Skill-detail updated command")
public record SkillDetailUpdatedCommand(
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[a-z0-9-]{36}$", message = "Invalid id")
        String skillDetailId,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Invalid code")
        String skillCode,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Invalid code")
        String studentCode,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[\\p{L}0-9 '-]{2,}$", message = "The full name must have at least 2 characters")
        String studentName,
        String teacherComment,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Invalid code")
        String teacherCode,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[\\p{L}0-9 '-]{2,}$", message = "The full name must have at least 2 characters")
        String teacherName,
        String rating) implements Serializable {}
