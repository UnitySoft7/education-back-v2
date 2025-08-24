package com.school.presence.student.daily.query.api.dto;
import com.school.presence.student.daily.query.api.response.PresenceStudentDailyResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
@Schema(name = "Lookup PresenceStudentDaily response")
public record LookupPresenceStudentDailyResponse(boolean success, PresenceStudentDailyResponse clothResponse) implements Serializable {}
