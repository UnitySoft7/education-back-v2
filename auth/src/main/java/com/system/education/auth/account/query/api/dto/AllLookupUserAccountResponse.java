package com.system.education.auth.account.query.api.dto;

import com.system.education.auth.account.query.api.response.UserAccountResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All user accounts lookup response")
public record AllLookupUserAccountResponse(
        boolean success, List<UserAccountResponse> userAccountResponses) implements Serializable {}