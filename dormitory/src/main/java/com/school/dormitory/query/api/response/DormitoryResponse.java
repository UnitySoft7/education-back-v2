package com.school.dormitory.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Dormitory Response")
public record DormitoryResponse(
        String dormitoryId,
        String name,
        String code,
        String category,
        String schoolYear,
        String logCreatedAt,
        String logCreatedDate,
        String logCreatedMonth,
        String logCreatedYear

) implements Serializable {}
