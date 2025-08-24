package com.school.presence.teacher.query.api.dto;
import com.school.presence.teacher.query.api.response.PresenceTeacherResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
@Schema(name = "Lookup PresenceTeacher response")
public record LookupPresenceTeacherResponse(boolean success, PresenceTeacherResponse presenceTeacherResponse) implements Serializable {}
