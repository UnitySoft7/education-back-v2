package com.system.education.auth.account.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "Auth role response")
public record AuthRoleResponse(
        String userId,
        String userCode,
        String username,
        String fullName,
        String roleName,
        List<String> permissions,
        String enterpriseCode,
        String enterpriseName
) implements Serializable { }
