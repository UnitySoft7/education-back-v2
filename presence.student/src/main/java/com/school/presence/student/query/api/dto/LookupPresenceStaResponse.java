package com.school.presence.student.query.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "Lookup PresenceInClass Response")
public record LookupPresenceStaResponse(boolean success, List<String> presenceStuResponse) implements Serializable {}
