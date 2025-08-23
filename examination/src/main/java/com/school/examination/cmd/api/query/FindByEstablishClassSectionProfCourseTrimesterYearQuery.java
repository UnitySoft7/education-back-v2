package com.school.examination.cmd.api.query;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

public record FindByEstablishClassSectionProfCourseTrimesterYearQuery(
        @NotNull(message = "Required") @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères") String establishmentCode,
        @NotNull(message = "Required") @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères") String classCode,
        @NotNull(message = "Required") @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères") String sectionCode,
        @NotNull(message = "Required") @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères") String profCode,
        @NotNull(message = "Required") @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères") String coursecode,
        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères") String semestre,
        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères") String year
) implements Serializable {
}
