package com.school.minos.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

@Schema(name = "Minos Payed Command")
public record MinosPayedCommand(
        @Positive(message = "Le montant doit etre positif") double amount,
        @NotNull(message = "Required") @Pattern(regexp = "^[0-9]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères") String ESCS,
        @NotNull(message = "Required") @Pattern(regexp = "^[0-9A-Z]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères") String minosCode,
        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères") String trimester
) implements Serializable {
}
