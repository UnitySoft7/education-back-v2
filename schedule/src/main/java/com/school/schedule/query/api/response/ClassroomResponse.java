package com.school.schedule.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Classroom Response")
public record ClassroomResponse(
        String classroomId,
        String name,
        String code) implements Serializable {}
