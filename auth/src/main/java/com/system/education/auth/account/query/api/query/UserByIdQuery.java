package com.system.education.auth.account.query.api.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "User by ID query")
public record UserByIdQuery(
        @NotNull(message = "Required")
        @Pattern(regexp = "^[a-z0-9-]{36}$", message = "L'Identifiant ne doit avoir que 36 caractères")
        String userId) implements Serializable {}
