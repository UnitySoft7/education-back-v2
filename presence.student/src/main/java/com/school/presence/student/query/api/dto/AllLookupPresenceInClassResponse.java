package com.school.presence.student.query.api.dto;

import com.school.presence.student.query.api.response.PresenceInClassResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup PresenceInClass Response")
public record AllLookupPresenceInClassResponse(boolean success, List<PresenceInClassResponse> presenceInClassResponses) implements Serializable {}
