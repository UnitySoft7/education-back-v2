package com.school.schedule.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Schedule Response")
public record ScheduleResponse(
        String scheduleId,
        String code,
        String establishmentName,
        String establishmentCode,
        String sectionName,
        String sectionCode,
        String classroomName,
        String classroomCode,
        DaysResponse monday,
        DaysResponse tuesday,
        DaysResponse wednesday,
        DaysResponse thusday,
        DaysResponse friday,
        DaysResponse saturday,
        DaysResponse sunday
      ) implements Serializable {}
