package com.system.education.student.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.codec.multipart.FilePart;

import java.io.Serializable;

@Schema(name = "Student update profile command")
public record StudentUpdateProfileCommand(
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Invalid student code")
        String studentCode,
        FilePart profile) implements Serializable {}
