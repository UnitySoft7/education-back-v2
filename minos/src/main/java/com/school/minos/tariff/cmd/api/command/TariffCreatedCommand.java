package com.school.minos.tariff.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

@Schema(name = "Tariff created command")
public record TariffCreatedCommand(
        @Positive(message = "Le montant doit etre positif") double amount,
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
