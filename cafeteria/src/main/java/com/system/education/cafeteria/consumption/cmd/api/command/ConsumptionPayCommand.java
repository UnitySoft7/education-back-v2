package com.system.education.cafeteria.consumption.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Consumption created command")
public record ConsumptionPayCommand(
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Le code de l'eleve est invalide")
        String studentCode,
        double amount,
        String semester,
        String schoolYear) implements Serializable {}
