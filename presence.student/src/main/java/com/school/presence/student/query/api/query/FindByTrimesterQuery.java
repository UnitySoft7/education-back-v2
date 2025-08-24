package com.school.presence.student.query.api.query;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

public record FindByTrimesterQuery(
        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères") String trimester) implements Serializable {
}
