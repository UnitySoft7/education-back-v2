package com.system.education.auth.role.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;
import java.util.List;

@Schema(name = "Role updated request")
public record RoleUpdatedRequest (
    @NotNull(message = "The field must not be null")
    @Pattern(regexp = "^[a-z0-9-]{36}$", message = "Ne doit contenir que trente-six caractères")
    String roleId,

    @NotNull(message = "The field must not be null")
    @Pattern(regexp = "^[a-zA-Z0-9'éèêëûùïàçîäìíôöúüҫ,\\s-_]{1,50}$", message = "Doit être composé entre 1 et 50 caractères seulement")
    String roleName,

    List<
        @NotNull(message = "The field must not be null")
        @Pattern(regexp = "^[A-Z_:]{3,50}$", message = "Doit être composé entre 3 et 50 caractères seulement")
        String> permissions) implements Serializable {}
