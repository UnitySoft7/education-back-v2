package com.school.presence.dormitory.student.query.api.dto;

import com.school.presence.dormitory.student.query.api.response.PresenceInDormitoryResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup PresenceInDormitory Response")
public record AllLookupPresenceInDormitoryResponse(boolean success, List<PresenceInDormitoryResponse> presenceInDormitoryResponses) implements Serializable {}
