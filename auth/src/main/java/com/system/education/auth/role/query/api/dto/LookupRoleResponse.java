package com.system.education.auth.role.query.api.dto;

import com.system.education.auth.role.query.api.response.RoleResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Role lookup response")
public record LookupRoleResponse (boolean success, RoleResponse roleResponse) implements Serializable {}