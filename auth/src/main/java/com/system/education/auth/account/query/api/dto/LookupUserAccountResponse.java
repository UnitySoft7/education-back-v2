package com.system.education.auth.account.query.api.dto;

import com.system.education.auth.account.query.api.response.UserAccountResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "User account lookup response")
public record LookupUserAccountResponse(
        boolean success, UserAccountResponse userAccountResponse) implements Serializable {}