package com.school.examination.cmd.api.query;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

public record FindByCodeQuery(
        @NotNull(message = "Ce champ est obligatoire")
        @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Le year est invalide")
        String code) implements Serializable {
}
