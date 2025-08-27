package com.school.examination.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

@Schema(name = "Examination created command")
public record ExaminationCreatedCommand(
        @NotNull(message = "Required")
        @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères")
        String profFullname,
        @NotNull(message = "Required")
        @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères")
        String studentFullname,
        @NotNull(message = "Required")
        @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères")
        String courseName,
        @NotNull(message = "Required")
        @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères")
        String examName,
        @NotNull(message = "Required")
        @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères")
        String establishmentName,
        @NotNull(message = "Required")
        @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères")
        String sectionName,
        @NotNull(message = "Required")
        @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères")
        String classroomName,
        @NotNull(message = "Required")
        @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères")
        String profCode,
        @NotNull(message = "Required")
        @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères")
        String studentCode,
        @NotNull(message = "Required")
        @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères")
        String courseCode,
        @NotNull(message = "Required")
        @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères")
        String examCode,
        @NotNull(message = "Required")
        @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères")
        String establishmentCode,
        @NotNull(message = "Required")
        @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères")
        String sectionCode,
        @NotNull(message = "Required")
        @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères")
        String classroomCode,
        @Pattern(regexp = "^\\d+$", message = "Ce champ doit etre un entier positif") String noteMax,
        @Positive(message = "Ce champ doit etre  positif ") String note,
        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,400}$", message = "Ce champ doit avoir de 2 jusqu'à 400 caractères")String comment,
        String semester, String schoolYear
) implements Serializable {
}
