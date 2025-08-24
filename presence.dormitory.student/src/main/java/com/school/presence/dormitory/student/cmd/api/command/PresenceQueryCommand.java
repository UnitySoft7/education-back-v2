package com.school.presence.dormitory.student.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.io.Serializable;

@Schema(name = "Presence Query Command")
public record PresenceQueryCommand(
        @NotNull(message = "Required") @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères")String student,
        @NotNull(message = "Required") @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères")String studentFullname,
        @NotNull(message = "Required") @Pattern(regexp = "^[A-Z]{6,10}$", message = "Ce champ doit avoir de 6 jusqu'à 10 caractères(Lettres majuscules")String status
) implements Serializable {}

