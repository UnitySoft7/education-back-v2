package com.system.education.infirmary.product.query.api.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Product by code query")
public record ProductByCodeQuery(
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9]{12,}$", message = "Invalid parent code")
        String code) implements Serializable {}
