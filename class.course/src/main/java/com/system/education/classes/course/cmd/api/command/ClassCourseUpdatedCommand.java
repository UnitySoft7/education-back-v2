package com.system.education.classes.course.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Establishment-section-class-course updated command")
public record ClassCourseUpdatedCommand(
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Le code est invalide")
        String classCourseCode,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Le code du class est invalide")
        String courseCode,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[\\p{L}0-9 '-]{2,}$", message = "The full name must have at least 2 characters")
        String courseName,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[0-9]{1,3}$", message = "Le nombre maximale de point doit avoir des chiffres seulement")
        String ponderation) implements Serializable {}
