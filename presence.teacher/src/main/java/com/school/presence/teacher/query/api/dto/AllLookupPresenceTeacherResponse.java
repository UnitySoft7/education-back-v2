package com.school.presence.teacher.query.api.dto;

import com.school.presence.teacher.query.api.response.PresenceTeacherResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup PresenceTeacher Response")
public record AllLookupPresenceTeacherResponse(boolean success, List<PresenceTeacherResponse> presenceTeacherResponses) implements Serializable {}
