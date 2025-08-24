package com.school.presence.student.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.List;

@Schema(name = "PresenceInClass created command")
public record PresenceInClassCreatedCommand(
        List<PresenceQueryCommand> presences,
        @NotBlank(message = "Le champ establishmentCode est requis") @Size(min = 2, max = 40, message = "establishmentCode doit avoir entre 2 et 40 caractères")@Schema(description = "Identifiant de l'enseignant ou autre", example = "ET1001")String pointer,
        @NotNull(message = "Required") @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères")String scheduleCode
) implements Serializable {}

