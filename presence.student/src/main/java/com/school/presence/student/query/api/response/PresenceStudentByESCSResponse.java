package com.school.presence.student.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Presence Student By studentCode Response")
public record PresenceStudentByESCSResponse(
        String presences,
        String scheduleCode,
        double effective,
        double absents,
        double presents,
        String schoolYear,
        String trimester
) implements Serializable {}
