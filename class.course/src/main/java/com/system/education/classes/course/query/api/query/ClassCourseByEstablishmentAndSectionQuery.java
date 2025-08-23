package com.system.education.classes.course.query.api.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Class course by establishment and section query")
public record ClassCourseByEstablishmentAndSectionQuery(
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Invalid establishment code")
        String establishmentCode,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Invalid section code")
        String sectionCode) implements Serializable {}
