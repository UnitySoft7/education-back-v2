package com.system.education.bulletin.detail.query.api.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Skill detail by code and semester query")
public record BulletinDetailByCodeAndSemesterQuery(
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Invalid parent code")
        String code,
        String semester,
        String schoolYear) implements Serializable {}
