package com.system.education.classes.course.query.api.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Class course by id query")
public record ClassCourseByIdQuery(
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[a-z0-9-]{36}$", message = "L'Identifiant ne doit avoir que 36 caractères")
        String id) implements Serializable {}
