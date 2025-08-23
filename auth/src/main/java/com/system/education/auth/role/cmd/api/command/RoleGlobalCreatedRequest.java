package com.system.education.auth.role.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Role global created request")
public record RoleGlobalCreatedRequest(
    @NotNull(message = "The field must not be null")
    @Pattern(regexp = "^[A-Z_]{3,25}$", message = "Doit être composé entre 3 et 25 caractères avec - seulement")
    String roleName) implements Serializable {}
