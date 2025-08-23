package com.school.classroom.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Classroom Response")
public record ClassroomResponse(
        String classroomId,
        String name,
        String codee,
        String frName,
        String enName,
        String sectionCode,
        String sectionName,
        String establishmentCode,
        String establishmentName) implements Serializable {}
