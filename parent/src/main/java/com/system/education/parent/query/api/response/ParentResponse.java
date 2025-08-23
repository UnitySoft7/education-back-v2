package com.system.education.parent.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Parent response")
public record ParentResponse(
        String parentId, String parentCode, String fullName, String citizenId,
        String phoneNo, String address, String logCreatedAt, String logCreatedMonth,
        String logCreatedYear, String logCreatedDate) implements Serializable {}
