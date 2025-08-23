package com.school.evaluate.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

@Schema(name = "Evaluate Reclaimed Command")
public record EvaluateReclaimedCommand(
        @NotNull(message = "Required") @Pattern(regexp = "^[0-9A-Z]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères")String evaluateCode,
        @Positive String note, String noteMax
) implements Serializable {
}
