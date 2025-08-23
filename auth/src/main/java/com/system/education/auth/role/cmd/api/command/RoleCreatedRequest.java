package com.system.education.auth.role.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;
import java.util.List;

@Schema(name = "Role created request")
public record RoleCreatedRequest (
    @NotNull(message = "The field must not be null")
    @Pattern(regexp = "^[A-Z_]{3,25}$", message = "Doit être composé entre 3 et 25 caractères avec - seulement")
    String roleName,

    @NotBlank(message = "Le nom de l’entreprise est obligatoire")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ0-9\\s\\-']{2,50}$", message = "Le nom de l’entreprise doit contenir entre 2 et 50 caractères")
    String enterpriseName,

    @Pattern(regexp = "^[a-zA-Z0-9\\-]{2,50}$", message = "Doit être composé de 2 à 50 caractères alphanumériques ou tirets uniquement")
    String enterpriseCode) implements Serializable {}
