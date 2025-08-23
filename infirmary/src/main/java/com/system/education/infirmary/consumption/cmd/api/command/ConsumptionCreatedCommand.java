package com.system.education.infirmary.consumption.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;
import java.util.List;

@Schema(name = "Consumption created command")
public record ConsumptionCreatedCommand(
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Le code de l'eleve est invalide")
        String studentCode,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[\\p{L} '-]{2,}$", message = "The full name must have at least 2 characters")
        String studentName,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Le code de l'etablissement est invalide")
        String establishmentCode,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[\\p{L} '-]{2,}$", message = "The full name must have at least 2 characters")
        String establishmentName,
        double amount,
        String semester,
        String schoolYear) implements Serializable {}
