package com.school.presence.dormitory.student.query.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "Lookup PresenceInDormitory Response")
public record LookupPresenceStaResponse(boolean success, List<String> presenceStuResponse) implements Serializable {}
