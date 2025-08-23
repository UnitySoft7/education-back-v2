
package com.system.education.auth.account.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Update password request")
public record PasswordUpdatedRequest(
        @NotNull(message = "The field must not be null")
        @Pattern(regexp = "^[A-Z0-9]{10,15}$", message = "Must be between 10 and 15 characters")
        String accountCode,
        @NotNull(message = "The field must not be null")
        String oldPassword,
        @NotNull(message = "The field must not be null")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.,;:#^()\\-_=+]).{8,}$", message = "Au moins 8 caractères, avec majuscule, minuscule, chiffre et caractère spécial")
        String newPassword,
        @NotNull(message = "The field must not be null")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.,;:#^()\\-_=+]).{8,}$", message = "Au moins 8 caractères, avec majuscule, minuscule, chiffre et caractère spécial")
        String verifyPassword) implements Serializable {}