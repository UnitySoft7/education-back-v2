package com.school.schedule.query.api.dto;
import com.school.schedule.query.api.response.ScheduleResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
@Schema(name = "Lookup Schedule response")
public record LookupScheduleResponse(boolean success, ScheduleResponse clothResponse) implements Serializable {}
