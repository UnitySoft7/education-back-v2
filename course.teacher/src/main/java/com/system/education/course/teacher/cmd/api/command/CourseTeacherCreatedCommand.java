package com.system.education.course.teacher.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Course-teacher created command")
public record CourseTeacherCreatedCommand(
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Le code de l'etablissement est invalide")
        String establishmentCode,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[\\p{L}0-9 '-]{2,}$", message = "The full name must have at least 2 characters")
        String establishmentName,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Le code du section est invalide")
        String sectionCode,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[\\p{L}0-9 '-]{2,}$", message = "The full name must have at least 2 characters")
        String sectionName,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Le code du class est invalide")
        String classCode,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[\\p{L}0-9 '-]+$", message = "Le nom doit avoir au moins 1 caractères")
        String className,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Le code du class est invalide")
        String courseCode,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[\\p{L}0-9 '-]{2,}$", message = "The full name must have at least 2 characters")
        String courseName,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Le code du proffesseur est invalide")
        String teacherCode,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[\\p{L}0-9 '-]{2,}$", message = "The full name must have at least 2 characters")
        String teacherName) implements Serializable {}
