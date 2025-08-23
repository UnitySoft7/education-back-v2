package com.school.minos.tariff.query.api.dto;

import com.school.minos.tariff.query.api.response.TariffResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup tariff Response")
public record AllLookupTariffResponse(boolean success, List<TariffResponse> tariffResponses) implements Serializable {}
