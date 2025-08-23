package com.school.evaluation.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

@Schema(name = "Evaluation created command")
public record EvaluationCreatedCommand(

        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères") String name,
        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères") String courseName,
        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères") String profFullname,
        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères") String establishmentName,
        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères") String sectionName,
        @NotNull(message = "Required") @Pattern(regexp = "^[A-Z0-9]{2,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères") String classroomName,
        @NotNull(message = "Required") @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères") String profCode,
        @NotNull(message = "Required") @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères") String courseCode,
        @NotNull(message = "Required") @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères") String establishmentCode,
        @NotNull(message = "Required") @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères") String sectionCode,
        @NotNull(message = "Required") @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères") String classroomCode,
        String schoolYear,
        String semester,
        @Pattern(regexp = "^\\d+$", message = "Ce champ doit etre un entier positif") String noteMax

) implements Serializable {
}

//        String trimester,
//        String schoolYear,