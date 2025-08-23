package com.system.education.establishment.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Add password request")
public record UserAccountCreatedRequest(
    @NotNull(message = "The field must not be null")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,25}$", message = "Doit être composé entre 3 et 55 caractères")
    String accountCode,
    @NotNull(message = "The field must not be null")
    @Pattern(regexp = "^[\\p{L} '-]{2,40}$", message = "Le nom doit avoir de 2 jusqu'à 40 caractères")
    String fullName,
    @NotNull(message = "The field must not be null")
    @Pattern(regexp = "^[A-Za-z0-9@.+ '-]{2,}$", message = "Le nom d'utilisateur doit avoir de 2 jusqu'à 40 caractères")
    String username,
    @NotNull(message = "The field must not be null")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.,;:#^()\\-_=+]).{8,}$", message = "Au moins 8 caractères, avec majuscule, minuscule, chiffre et caractère spécial")
    String password,
    @NotNull(message = "The field must not be null")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.,;:#^()\\-_=+]).{8,}$", message = "Au moins 8 caractères, avec majuscule, minuscule, chiffre et caractère spécial")
    String verifyPassword,
    @NotNull(message = "The field must not be null")
    @Pattern(regexp = "^[_a-zA-Z0-9-]{2,40}$", message = "Ne doit avoir que 36 caractères")
    String role,

    @NotNull(message = "The field is required")
    @Pattern(regexp = "^[\\p{L} '-]{2,}$", message = "The full name must have at least 2 characters")
    String establishmentName,

    @Pattern(regexp = "^[a-zA-Z0-9\\-]{2,50}$", message = "Doit être composé de 2 à 50 caractères alphanumériques ou tirets uniquement")
    String enterpriseCode) implements Serializable {}
