package com.school.bed.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

@Schema(name = "Bed created command")
public record BedCreatedCommand(
        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères")String sigle
) implements Serializable {}
