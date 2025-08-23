package com.school.schedule.cmd.api.query;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

public record FindByCodeAndIndexQuery(
        @NotNull(message = "Ce champ est obligatoire") @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Le code est invalide") String code,
        @NotNull(message = "Ce champ est obligatoire") @Pattern(regexp = "^[A-Z0-9]{1,}$", message = "Le code est invalide") String index
) implements Serializable {
}
