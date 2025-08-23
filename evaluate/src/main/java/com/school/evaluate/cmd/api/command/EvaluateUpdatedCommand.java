package com.school.evaluate.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

@Schema(name = "Evaluate updated command")
public record EvaluateUpdatedCommand(
        @NotNull(message = "Required") @Pattern(regexp = "^[0-9A-Z]{6,20}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères") String evaluateCode,
        double note, String schoolYear, String semester) implements Serializable {
}
