package com.system.education.establishment.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Establishment updated command")
public record EstablishmentUpdatedCommand(
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Invalid establishment code")
        String establishmentCode,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[\\p{L} '-]{2,}$", message = "The full name must have at least 2 characters")
        String nickName,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[0-9]{8,15}", message = "Le numero telephonique doit avoir de 2 jusqu'à 40 caractères")
        String phoneNo,
        String address,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "L'adresse email doit etre comme 'example@example.com'")
        String email,
        String site,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[\\p{L} '-]{2,}$", message = "Le province doit avoir au moins 2 caractères")
        String province,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[\\p{L} '-]{2,}$", message = "La commune doit avoir au moins 2 caractères")
        String commune,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[\\p{L} '-]{2,}$", message = "La zone doit avoir au moins 2 caractères")
        String zone,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[\\p{L} '-]{2,}$", message = "Le quartier doit avoir au moins 2 caractères")
        String quarter,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[\\p{L} '-]{2,}$", message = "La localite doit avoir au moins 2 caractères")
        String locality) implements Serializable {}
