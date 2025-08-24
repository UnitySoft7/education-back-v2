package com.school.presence.student.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "PresenceInClass Response")
public record PresenceInClassResponse(
        String presenceInClassId,
        String presenceInClassCode,
        List<PresenceQueryResponse> presences,
        String ET,
        String scheduleCode,
        double effective,
        double absents,
        double presents,
        String schoolYear,
        String trimester,
        String status,
        String logCreatedAt,
        String logCreatedDate,
        String logCreatedMonth,
        String logCreatedYear) implements Serializable {}
