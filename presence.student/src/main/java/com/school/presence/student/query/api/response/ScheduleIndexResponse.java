package com.school.presence.student.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Schedule Index Response")
public record ScheduleIndexResponse(
        String scheduleName,
        String code,
        String startOn,
        String endOn,
        String ESC,
        String ESCC,
        String establishmentName,
        String establishmentCode,
        String sectionName,
        String sectionCode,
        String courseCode,
        String classroomName,
        String classroomCode,
        String day,
        String index,
        String dayIndexCourse
      ) implements Serializable {}
