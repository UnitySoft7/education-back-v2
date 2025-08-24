package com.school.presence.dormitory.student.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.List;

@Schema(name = "PresenceInDormitory created command")
public record PresenceInDormitoryCreatedCommand(
        List<PresenceQueryCommand> presences,
        @NotBlank(message = "Le champ prof est requis")
        @Size(min = 2, max = 40, message = "prof doit avoir entre 2 et 40 caractères")
        @Schema(description = "Identifiant de l'enseignant ou autre", example = "ET001")
        String prof
) implements Serializable {}

