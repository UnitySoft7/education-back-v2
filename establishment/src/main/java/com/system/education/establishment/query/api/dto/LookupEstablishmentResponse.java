package com.system.education.establishment.query.api.dto;

import com.system.education.establishment.query.api.response.EstablishmentResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup establishment response")
public record LookupEstablishmentResponse(
        boolean success, EstablishmentResponse establishmentResponse) implements Serializable {}
