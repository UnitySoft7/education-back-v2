package com.system.education.auth.account.query.api.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "User by code query")
public record UserByCodeQuery(
        @NotNull(message = "Required")
        @Pattern(regexp = "^[A-Z0-9]{5,}$", message = "Le code doit contenir au moins 5 caractères alphanumériques en majuscules")
        String code) implements Serializable {}
