package com.system.education.bulletin.bulletin.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Bulletin response")
public record BulletinResponse(
        String bulletinId, String bulletinCode, String studentCode,
        String studentName, String dateOfBirth, String daysPresent,
        String daysAbsent, String daysSick, String otherReason,
        String percent, String grade, String homeRoomTeacherCode,
        String homeRoomTeacherName, String homeRoomTeacherComment,
        String academicDirectorRemark, String semester, String schoolYear,
        String classCode, String className, String establishmentCode,
        String establishmentName, String logCreatedAt, String logCreatedMonth,
        String logCreatedYear, String logCreatedDate) implements Serializable {}
