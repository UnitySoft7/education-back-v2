package com.system.education.cafeteria.product.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

@Schema(name = "Product created command")
public record ProductCreatedCommand(
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[\\p{L}0-9 '-]{2,}$", message = "The full name must have at least 2 characters")
        String productName,
        @NotNull(message = "The field is required")
        @Positive(message = "Le montant doit etre positif")
        double amount,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Le code de l'etablissement est invalide")
        String establishmentCode,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[\\p{L} '-]{2,}$", message = "The full name must have at least 2 characters")
        String establishmentName) implements Serializable {}
