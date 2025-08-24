package com.school.dormitory.daily.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "DormitoryStudentDaily Response")
public record DormitoryStudentDailyResponse(
        String dormitoryStudentDailyId,
        String code,
        String dormitoryInClassCode,
        String dormitory,
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