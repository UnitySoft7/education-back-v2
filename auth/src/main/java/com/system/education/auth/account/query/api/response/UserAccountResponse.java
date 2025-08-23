package com.system.education.auth.account.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "User account response")
public record UserAccountResponse(
        String userAccountId, String userCode, String full_name,
        String username, String role, String roleName, String status) implements Serializable {}
