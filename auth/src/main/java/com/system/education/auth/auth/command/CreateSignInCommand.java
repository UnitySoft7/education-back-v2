package com.system.education.auth.auth.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Create authenticate command")
public record CreateSignInCommand(
    @NotNull(message = "Ne doit pas être nul")
    String username,
    @NotNull(message = "Ne doit pas être nul")
    String password
) implements Serializable {}
