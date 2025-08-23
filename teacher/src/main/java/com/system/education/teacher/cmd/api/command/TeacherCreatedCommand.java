package com.system.education.teacher.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Teacher created command")
public record TeacherCreatedCommand(
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[\\p{L} '-]{2,}$", message = "The full name must have at least 2 characters")
        String fullName,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9-/.]{1,20}$", message = "L'Identifiant ne doit avoir de 1 jusqu'à 20 caracteres")
        String citizenId,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[0-9]{8,15}", message = "Le numero telephonique doit avoir de 2 jusqu'à 40 caractères")
        String phoneNo,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9-/.]+$", message = "La matricule ne doit avoir au moins 1 caractère")
        String matricule,
        @NotNull(message = "The field is required")
        String address,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z]{5}$", message = "Le genre ne doit avoir que 5 caracteres")
        String gender,
        String function,
        @NotNull(message = "The field must not be null")
        @Pattern(regexp = "^[A-Za-z0-9@.+ '-]{2,}$", message = "Le nom d'utilisateur doit avoir de 2 jusqu'à 40 caractères")
        String username,
        @NotNull(message = "The field must not be null")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.,;:#^()\\-_=+]).{8,}$", message = "Au moins 8 caractères, avec majuscule, minuscule, chiffre et caractère spécial")
        String password,
        @NotNull(message = "The field must not be null")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.,;:#^()\\-_=+]).{8,}$", message = "Au moins 8 caractères, avec majuscule, minuscule, chiffre et caractère spécial")
        String verifyPassword,
        @NotNull(message = "The field must not be null")
        @Pattern(regexp = "^[a-zA-Z0-9-]{36}$", message = "Ne doit avoir que 36 caractères")
        String role,
        String establishmentCode,
        String establishmentName) implements Serializable {}
