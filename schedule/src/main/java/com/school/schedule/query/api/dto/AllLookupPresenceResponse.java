package com.school.schedule.query.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "AllLookup Presence Response")
public record AllLookupPresenceResponse(boolean success, List<String> dayResponses) implements Serializable {}
