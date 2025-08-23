package com.system.education.permission.permission.query.api.dto;

import com.system.education.permission.permission.query.api.response.PermissionResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup permission response")
public record LookupPermissionResponse(
        boolean success, PermissionResponse permissionResponse) implements Serializable {}
