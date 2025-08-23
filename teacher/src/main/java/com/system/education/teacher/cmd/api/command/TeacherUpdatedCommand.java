package com.system.education.teacher.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Teacher updated command")
public record TeacherUpdatedCommand(
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Invalid code")
        String teacherCode,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[0-9]{8,15}", message = "Le numero telephonique doit avoir de 2 jusqu'à 40 caractères")
        String phoneNo,
        @NotNull(message = "The field is required")
        String address) implements Serializable {}
