package com.school.minos.tariff.query.api.dto;

import com.school.minos.tariff.query.api.response.TariffResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup tariff response")
public record LookupTariffResponse(boolean success, TariffResponse tariffResponse) implements Serializable {}
