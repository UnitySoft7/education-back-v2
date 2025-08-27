package com.school.minos.minos.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Minos Response")
public record MinosResponse(
        String minosId,
        String minosCode,
        String studentCode,
        String studentFullname,
        String semester,
        double amount,
        double maxamount,
        String status,
        String schoolYear,
        String establishmentName,
        String establishmentCode,
        String sectionName,
        String sectionCode,
        String classroomName,
        String classroomCode,
        String logCreatedAt,
        String logCreatedDate,
        String logCreatedMonth,
        String logCreatedYear) implements Serializable {}
