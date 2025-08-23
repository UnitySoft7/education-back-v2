package com.system.education.parent.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;
import java.util.List;

@Schema(name = "Parent created command")
public record ParentCreatedCommand(
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[\\p{L} '-]{2,}$", message = "The full name must have at least 2 characters")
        String fullName,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9-/.]{1,20}$", message = "L'Identifiant ne doit avoir de 1 jusqu'à 20 caracteres")
        String citizenId,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[0-9]{8,15}", message = "Le numero telephonique doit avoir de 2 jusqu'à 40 caractères")
        String phoneNo,
        @NotNull(message = "The field is required")
        String address,
        String username,
        String password,
        String verifyPassword,
        List<String> students) implements Serializable {}
