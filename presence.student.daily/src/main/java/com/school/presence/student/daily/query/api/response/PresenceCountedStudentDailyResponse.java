package com.school.presence.student.daily.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "PresenceStudentDaily Response")
public record PresenceCountedStudentDailyResponse(
        String code,
        double absents,
        double presents,
        String schoolYear,
        String trimester
) implements Serializable {}