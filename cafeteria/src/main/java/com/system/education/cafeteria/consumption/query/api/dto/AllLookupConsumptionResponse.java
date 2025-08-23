package com.system.education.cafeteria.consumption.query.api.dto;

import com.system.education.cafeteria.consumption.query.api.response.ConsumptionResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All lookup consumption response")
public record AllLookupConsumptionResponse(
        boolean success, List<ConsumptionResponse> consumptionResponses) implements Serializable {}
