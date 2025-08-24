package com.school.presence.teacher.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "PresenceTeacher Response")
public record PresenceTeacherResponse(
        String presenceTeacherId,
        String code,
        String prof,
        String profName,
        String pointer,
        String pointerName,
        String departHour,
        String day,
        String trimester,
        String schoolYear,
        String logCreatedAt,
        String logCreatedDate,
        String logCreatedMonth,
        String logCreatedYear
) implements Serializable {}
