package com.school.presence.student.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Presence Stu Response")
public record PresenceStuResponse(
        String status
) implements Serializable {}
