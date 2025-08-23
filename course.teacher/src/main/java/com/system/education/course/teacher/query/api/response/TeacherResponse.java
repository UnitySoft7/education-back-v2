package com.system.education.course.teacher.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Teacher response")
public record TeacherResponse(
        String teacherId, String teacherCode, String fullName, String citizenId,
        String phoneNo, String matricule, String address, String gender,
        String logCreatedAt, String logCreatedMonth, String logCreatedYear,
        String logCreatedDate) implements Serializable {}
