package com.system.education.permission.permission.query.api.dto;

import com.system.education.permission.permission.query.api.response.PermissionResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All lookup permission response")
public record AllLookupPermissionResponse(
        boolean success, List<PermissionResponse> permissionResponses) implements Serializable {}
