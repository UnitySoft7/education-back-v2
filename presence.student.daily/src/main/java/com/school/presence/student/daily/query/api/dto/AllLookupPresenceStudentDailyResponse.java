package com.school.presence.student.daily.query.api.dto;

import com.school.presence.student.daily.query.api.response.PresenceStudentDailyResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup PresenceStudentDaily Response")
public record AllLookupPresenceStudentDailyResponse(boolean success, List<PresenceStudentDailyResponse> presenceStudentDailyResponses,double effectivePresence,double effectiveAbscence) implements Serializable {}
