package com.system.education.establishment.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.codec.multipart.FilePart;

import java.io.Serializable;

@Schema(name = "Establishment created command")
public record EstablishmentCreatedCommand(
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[\\p{L} '-]{2,}$", message = "The full name must have at least 2 characters")
        String establishmentName,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[\\p{L} '-]{2,}$", message = "The full name must have at least 2 characters")
        String nickName,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[0-9]{5,20}$", message = "Le NIF doit avoir de 5 jusqu'à 20 chiffres")
        String nif,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[0-9/.]*", message = "Le RC doit avoir de 5 jusqu'à 20 chiffres")
        String rc,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[0-9]{8,15}", message = "Le numero telephonique doit avoir de 2 jusqu'à 40 caractères")
        String phoneNo,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "L'adresse email doit etre comme 'example@example.com'")
        String email,
        String site,
        FilePart logo,
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
        String locality,
        @NotNull(message = "The field must not be null")
        @Pattern(regexp = "^[A-Za-z0-9@.+ '-]{2,}$", message = "Le nom d'utilisateur doit avoir de 2 jusqu'à 40 caractères")
        String username,
        @NotNull(message = "The field must not be null")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.,;:#^()\\-_=+]).{8,}$", message = "Au moins 8 caractères, avec majuscule, minuscule, chiffre et caractère spécial")
        String password,
        @NotNull(message = "The field must not be null")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.,;:#^()\\-_=+]).{8,}$", message = "Au moins 8 caractères, avec majuscule, minuscule, chiffre et caractère spécial")
        String verifyPassword) implements Serializable {}
