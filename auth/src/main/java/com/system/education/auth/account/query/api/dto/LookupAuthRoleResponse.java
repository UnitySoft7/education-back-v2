package com.system.education.auth.account.query.api.dto;

import com.system.education.auth.account.query.api.response.AuthRoleResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup auth role response")
public record LookupAuthRoleResponse(Boolean success, AuthRoleResponse authRoleResponse) implements Serializable { }
