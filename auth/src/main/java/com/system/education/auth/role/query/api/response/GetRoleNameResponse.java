package com.system.education.auth.role.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Get Role name response")
public record GetRoleNameResponse(String role) implements Serializable {}
