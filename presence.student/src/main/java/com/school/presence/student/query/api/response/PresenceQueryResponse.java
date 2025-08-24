package com.school.presence.student.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Presence Query Response")
public record PresenceQueryResponse(
       String ESCS,
       String studentFullname,
       String status
) implements Serializable {}

