package com.school.schedule.cmd.api.query;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

public record FindByCodeQuery(
        @NotNull(message = "Ce champ est obligatoire")
        @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Le code est invalide")
        String code) implements Serializable {
}
