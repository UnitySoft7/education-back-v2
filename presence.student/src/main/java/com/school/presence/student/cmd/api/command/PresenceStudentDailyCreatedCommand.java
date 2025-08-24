package com.school.presence.student.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "PresenceStudentDaily created command")
public record PresenceStudentDailyCreatedCommand(
        String presenceInClassCode,
        String presence,
        String pointer,
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
