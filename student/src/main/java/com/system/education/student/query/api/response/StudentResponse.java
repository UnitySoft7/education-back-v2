package com.system.education.student.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Student response")
public record StudentResponse(
        String studentId, String studentCode, String fullName, String birthday,
        String gender, String address, String fatherName, String fatherMobileNo,
        String motherName, String motherMobileNo, String parentCode, String parentName,
        String schoolYear, String establishmentCode, String establishmentName,
        String sectionCode, String sectionName, String classCode, String className,
        String logCreatedAt, String logCreatedMonth, String logCreatedYear,
        String logCreatedDate) implements Serializable {}
