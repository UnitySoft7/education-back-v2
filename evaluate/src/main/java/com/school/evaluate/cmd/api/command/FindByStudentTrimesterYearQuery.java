package com.school.evaluate.cmd.api.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

public record FindByStudentTrimesterYearQuery(
        @NotNull(message = "Required") @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères") String student,
        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères") String semestre,
//        @NotNull(message = "Required") @Pattern(regexp = "^[0-9-]{9}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères")
        String year
) implements Serializable {
}
