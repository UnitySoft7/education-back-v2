package com.school.dormitory.daily.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "DormitoryStudentDaily created command")
public record DormitoryStudentDailyCreatedCommand(
        String dormitoryInClassCode,
        String dormitory,
        String prof,
        String student,
        String scheduleCode,
        double effective,
        double absents,
        double presents,
        String schoolYear,
        String trimester,
        String status
) implements Serializable {
}
