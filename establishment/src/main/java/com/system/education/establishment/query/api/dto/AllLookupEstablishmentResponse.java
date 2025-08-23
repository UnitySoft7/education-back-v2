package com.system.education.establishment.query.api.dto;

import com.system.education.establishment.query.api.response.EstablishmentResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All lookup establishment response")
public record AllLookupEstablishmentResponse(
        boolean success, List<EstablishmentResponse> establishmentResponses) implements Serializable {}
