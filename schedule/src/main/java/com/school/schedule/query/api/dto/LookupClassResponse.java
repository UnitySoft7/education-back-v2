package com.school.schedule.query.api.dto;

import com.school.schedule.query.api.response.ScheduleClassResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "Lookup Class Response")
public record LookupClassResponse(
        boolean success, List<ScheduleClassResponse> scheduleClassResponses) implements Serializable {}
