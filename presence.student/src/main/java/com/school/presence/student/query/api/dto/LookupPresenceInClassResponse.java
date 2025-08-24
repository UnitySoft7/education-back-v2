package com.school.presence.student.query.api.dto;

import com.school.presence.student.query.api.response.PresenceInClassResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup PresenceInClass Response")
public record LookupPresenceInClassResponse(boolean success, PresenceInClassResponse presenceInClassResponse) implements Serializable {}
