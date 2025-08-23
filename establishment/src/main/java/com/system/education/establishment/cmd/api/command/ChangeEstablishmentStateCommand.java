package com.system.education.establishment.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Change establishment state command")
public record ChangeEstablishmentStateCommand(
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Invalid establishment code")
        String establishmentCode) implements Serializable {
}
