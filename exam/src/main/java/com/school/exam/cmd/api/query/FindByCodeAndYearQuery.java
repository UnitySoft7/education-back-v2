package com.school.exam.cmd.api.query;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

public record FindByCodeAndYearQuery(
        @NotNull(message = "Ce champ est obligatoire") @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Le year est invalide") String code,
        @NotNull(message = "Ce champ est obligatoire") @Pattern(regexp = "^[-0-9]{9}$", message = "Le year est invalide") String year) implements Serializable {
}
