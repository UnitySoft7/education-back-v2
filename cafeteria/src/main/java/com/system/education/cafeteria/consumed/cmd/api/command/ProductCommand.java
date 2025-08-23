package com.system.education.cafeteria.consumed.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

@Schema(name = "Product command")
public record ProductCommand(
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Invalid product code")
        String productCode,

        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[\\p{L}0-9 '-]{2,}$", message = "The full name must have at least 2 characters")
        String productName,

        String qty,

        double amount) implements Serializable {}
