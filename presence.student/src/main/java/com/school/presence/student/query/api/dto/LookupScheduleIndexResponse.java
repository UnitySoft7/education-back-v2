package com.school.presence.student.query.api.dto;

import com.school.presence.student.query.api.response.ScheduleIndexResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup Schedule Index Response")
public record LookupScheduleIndexResponse(boolean success, ScheduleIndexResponse scheduleIndexResponse) implements Serializable {}
