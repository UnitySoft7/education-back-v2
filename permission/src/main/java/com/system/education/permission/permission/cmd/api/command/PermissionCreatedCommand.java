package com.system.education.permission.permission.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Permission created command")
public record PermissionCreatedCommand(
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{5,}$", message = "Invalid code")
        String permissionType,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Invalid code")
        String studentCode,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[\\p{L}0-9 '-]{2,}$", message = "The full name must have at least 2 characters")
        String studentName,
        String description,
        String startOn,
        String endOn,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Le code de l'etablissement est invalide")
        String establishmentCode,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[\\p{L} '-]{2,}$", message = "The full name must have at least 2 characters")
        String establishmentName,
        String semester,
        String schoolYear) implements Serializable {}
