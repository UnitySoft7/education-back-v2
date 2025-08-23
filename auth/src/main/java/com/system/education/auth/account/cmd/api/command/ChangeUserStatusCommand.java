package com.system.education.auth.account.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "User enable request")
public record ChangeUserStatusCommand(
        @NotNull(message = "Required")
        @Pattern(regexp = "^[A-Z0-9-]{8,15}$", message = "Le code doit avoir de 9 jusqu'à 15 caractères")
        String user_code) implements Serializable {}