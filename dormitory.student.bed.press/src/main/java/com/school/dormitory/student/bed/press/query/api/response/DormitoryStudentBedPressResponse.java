package com.school.dormitory.student.bed.press.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "DormitoryStudentBedPress Response")
public record DormitoryStudentBedPressResponse(
        String dormitoryStudentBedPressId,
        String code,
        String dormitory,
        String student,
        String bed,
        String press,
        String logCreatedAt,
        String logCreatedDate,
        String logCreatedMonth,
        String logCreatedYear)implements Serializable{};