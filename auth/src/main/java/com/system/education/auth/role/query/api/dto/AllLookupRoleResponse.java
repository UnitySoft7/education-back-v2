package com.system.education.auth.role.query.api.dto;

import com.system.education.auth.role.query.api.response.RoleResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All role lookup response")
public record AllLookupRoleResponse (boolean success, List<RoleResponse> roleResponses) implements Serializable {}