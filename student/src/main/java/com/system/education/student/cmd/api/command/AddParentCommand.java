package com.system.education.student.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Add parent command")
public record AddParentCommand(
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Invalid student code")
        String studentCode,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Invalid parent code")
        String parentCode,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[\\p{L} '-]{2,}$", message = "The full name must have at least 2 characters")
        String parentName) implements Serializable {}
