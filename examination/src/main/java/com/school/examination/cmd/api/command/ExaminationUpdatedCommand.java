package com.school.examination.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Examination updated command")
public record ExaminationUpdatedCommand(
        @NotNull(message = "Required") @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères") String examinationCode,
        @Pattern(regexp = "^\\d+$", message = "Ce champ doit contenir des entiers") String note


) implements Serializable {
}



