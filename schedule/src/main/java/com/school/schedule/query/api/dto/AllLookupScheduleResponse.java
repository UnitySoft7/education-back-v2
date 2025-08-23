package com.school.schedule.query.api.dto;

import com.school.schedule.query.api.response.ScheduleResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup Schedule Response")
public record AllLookupScheduleResponse(boolean success, List<ScheduleResponse> ScheduleResponses) implements Serializable {}
