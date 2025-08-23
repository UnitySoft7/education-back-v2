package com.school.minos.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

@Schema(name = "Minos created command")
public record MinosCreatedCommand(
        @Positive(message = "Le montant doit etre positif") double maxamount,
        String studentCode,
        String studentName,
        String semester,
        @NotNull(message = "Required")
        @Pattern(regexp = "^[0-9-]{9}$", message = "Ce champ ne doit avoir que 10 caractères")
        String schoolYear,
        String sectionCode,
        String sectionName,
        String classCode,
        String className,
        @NotNull(message = "Required")
        @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères")
        String establishmentName,
        @NotNull(message = "Required")
        @Pattern(regexp = "^[0-9A-Z]{6,20}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères")
        String establishmentCode

) implements Serializable {}




//        @NotNull(message = "Required") @Pattern(regexp = "^[0-9]{7}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères")String ESCCT,
//        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères") String studentFullname,
//        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères") String semester,
//        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères")String sectionName,
//        @NotNull(message = "Required") @Pattern(regexp = "^[0-9A-Z]{6,20}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères")String sectionCode,
//        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères")String classroomName,
//        @NotNull(message = "Required") @Pattern(regexp = "^[0-9A-Z]{6,20}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères")String classroomCode


