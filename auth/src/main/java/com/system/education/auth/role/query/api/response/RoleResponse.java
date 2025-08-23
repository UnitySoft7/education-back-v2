package com.system.education.auth.role.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "Role response")
public record RoleResponse (
        String roleId, String roleName, List<String> permissions,
        String enterpriseName, String enterpriseCode) implements Serializable {}
