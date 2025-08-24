package com.school.presence.dormitory.student.query.api.dto;
import com.school.presence.dormitory.student.query.api.response.PresenceInDormitoryResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
@Schema(name = "Lookup PresenceInDormitory Response")
public record LookupPresenceInDormitoryResponse(boolean success, PresenceInDormitoryResponse presenceInDormitoryResponse) implements Serializable {}
