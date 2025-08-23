package com.system.education.cafeteria.consumed.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Pay all product command")
public record PayAllProductCommand(
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Invalid product code")
        String studentCode,
        String semester,
        String schoolYear) implements Serializable {}
