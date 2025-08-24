package com.school.presence.dormitory.student.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "PresenceInDormitory Response")
public record PresenceInDormitoryResponse(
        String presenceInDormitoryId,
        String presenceInDormitoryCode,
        String domitoryCode,
        List<PresenceQueryResponse> presences,
        String ET,
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
