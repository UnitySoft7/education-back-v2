package com.school.exam.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Exam updated command")
public record ExamUpdatedCommand(
        @NotNull(message = "Required") @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères") String examCode,
        @Pattern(regexp = "^[0-9]{1,3}$", message = "Ce champ doit être un entier positif entre 1 et 999") String noteMax
) implements Serializable {}
