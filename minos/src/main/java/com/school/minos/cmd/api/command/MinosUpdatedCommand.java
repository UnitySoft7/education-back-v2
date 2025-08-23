package com.school.minos.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

@Schema(name = "Minos updated command")
public record MinosUpdatedCommand(
        @NotNull(message = "Required") @Pattern(regexp = "^[0-9A-Z]{6,20}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères")String minosCode,
        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères") String trimester,
        @Positive(message = "Ce champ doit etre positif") double amount,
        @NotNull(message = "Required") @Pattern(regexp = "^[0-9]{7}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères")String schoolYear
) implements Serializable {}
//
//@NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères")String establishmentName,
//@NotNull(message = "Required") @Pattern(regexp = "^[0-9A-Z]{6,20}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères")String establishmentCode,
//@NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères")String sectionName,
//@NotNull(message = "Required") @Pattern(regexp = "^[0-9A-Z]{6,20}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères")String sectionCode,
//@NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères")String classroomName,
//@NotNull(message = "Required") @Pattern(regexp = "^[0-9A-Z]{6,20}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères")String classroomCode,

//@NotNull(message = "Required") @Pattern(regexp = "^[0-9]{7}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères")String ESCCT,
//@NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères") String studentFullname,
