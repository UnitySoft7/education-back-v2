package com.system.education.permission.permission.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Permission response")
public record PermissionResponse(
        String permissionId, String permissionCode, String permissionType, String description,
        String startOn, String endOn, String studentCode, String studentName, String semester,
        String schoolYear, String establishmentCode, String establishmentName, String logCreatedAt,
        String logCreatedMonth, String logCreatedYear, String logCreatedDate
) implements Serializable {}
