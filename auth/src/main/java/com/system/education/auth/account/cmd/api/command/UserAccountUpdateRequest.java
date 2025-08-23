package com.system.education.auth.account.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "User account update role request")
public record UserAccountUpdateRequest(
    @NotNull(message = "The field must not be null")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,25}$", message = "Doit être composé entre 3 et 55 caractères")
    String accountCode,
    @NotNull(message = "The field must not be null")
    @Pattern(regexp = "^[a-zA-Z0-9-]{36}$", message = "Ne doit avoir que 36 caractères")
    String role) implements Serializable {}
