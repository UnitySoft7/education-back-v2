package com.school.presence.student.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

@Schema(name = "PresenceInClass updated command")
public record PresenceInClassUpdatedCommand(
        @NotNull(message = "Required") @Pattern(regexp = "^[0-9A-Z]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères")String presenceInClassCode,
        String ESCS,
        double effective,
        double absents,
        double presents
) implements Serializable {}
