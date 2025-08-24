package com.school.presence.student.daily.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "PresenceStudentDaily Response")
public record PresenceStudentDailyResponse(
        String presenceStudentDailyId,
        String code,
        String presenceInClassCode,
        String presence,
        String ESCS,
        String presentStatus,
        String ET,
        String scheduleCode,
        double absents,
        double presents,
        String schoolYear,
        String trimester,
        String status,
        String logCreatedAt
) implements Serializable {}