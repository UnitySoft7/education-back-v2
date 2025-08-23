package com.system.education.parent.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Parent updated command")
public record ParentUpdatedCommand(
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Invalid parent code")
        String parentCode,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[0-9]{8,15}", message = "Le numero telephonique doit avoir de 2 jusqu'à 40 caractères")
        String phoneNo,
        @NotNull(message = "The field is required")
        String address) implements Serializable {}
