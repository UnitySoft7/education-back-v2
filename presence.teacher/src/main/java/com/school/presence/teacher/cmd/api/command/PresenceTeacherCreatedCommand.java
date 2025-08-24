package com.school.presence.teacher.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

@Schema(name = "PresenceTeacher created command")
public record PresenceTeacherCreatedCommand(
        @NotNull(message = "Required") @Pattern(regexp ="^[\\p{L} '-]{2,}$", message = "The full name must have at least 2 characters")String pointerName,
        @NotNull(message = "Required") @Pattern(regexp ="^[A-Z0-9]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères")String pointer,
        @NotNull(message = "Required") @Pattern(regexp ="^[\\p{L} '-]{2,}$", message = "The full name must have at least 2 characters")String profName,
        @NotNull(message = "Required") @Pattern(regexp ="^[A-Z0-9]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères")String prof
) implements Serializable {}
