package com.school.presence.dormitory.student.query.api.query;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

public record FindByYearQuery(
        @NotNull(message = "Ce champ est obligatoire")
        @Pattern(regexp = "^[-0-9]{9}$", message = "Le year est invalide")
        String year) implements Serializable {
}
