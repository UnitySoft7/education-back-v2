package com.system.education.infirmary.consumption.query.api.dto;

import com.system.education.infirmary.consumption.query.api.response.ConsumptionResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup consumption response")
public record LookupConsumptionResponse(
        boolean success, ConsumptionResponse consumptionResponse) implements Serializable {}
