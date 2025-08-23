package com.school.schedule.query.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup Days Response")
public record AllLookupDaysResponse(boolean success, List<String> dayResponses) implements Serializable {}
