package com.school.evaluation.query.api.dto;

import com.school.evaluation.query.api.response.EstablishmentSectionClassResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup establishment section class response")
public record LookupEstablishmentSectionClassResponse(
        boolean success, EstablishmentSectionClassResponse establishmentSectionClassResponse) implements Serializable {}
