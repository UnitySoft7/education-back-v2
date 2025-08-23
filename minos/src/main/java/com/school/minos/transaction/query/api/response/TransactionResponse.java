package com.school.minos.transaction.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
@Schema(name = "TRansaction Response")
public record TransactionResponse(
        String transaId, String transaCode, String minosCode, String studentCode,
        String studentFullname, String trimester, double amount, double maxamount,
        String status, String schoolYear, String establishmentName, String establishmentCode,
        String sectionName, String sectionCode, String classroomName, String classroomCode,
        String logCreatedAt, String logCreatedDate, String logCreatedMonth,
        String logCreatedYear) implements Serializable {
}
