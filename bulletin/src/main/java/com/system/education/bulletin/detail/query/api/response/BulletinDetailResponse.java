package com.system.education.bulletin.detail.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Bulletin detail response")
public record BulletinDetailResponse(
        String bulletinDetailId,
        String studentCode,
        String studentName,
        String courseCode,
        String courseName,
        String midTermAssessment,
        String examination,
        String average,
        String grade,
        String teacherComment,
        String teacherCode,
        String teacherName,
        String semester,
        String schoolYear,
        String classCode,
        String className,
        String establishmentCode,
        String establishmentName,
        String logCreatedAt,
        String logCreatedMonth,
        String logCreatedYear,
        String logCreatedDate) implements Serializable {}
