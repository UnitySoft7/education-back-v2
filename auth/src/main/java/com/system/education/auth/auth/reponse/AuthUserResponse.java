package com.system.education.auth.auth.reponse;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "Auth response")
public record AuthUserResponse(
        String fullName, String username, String user_code,
        String establishment_code, String establishment_name,
        String createdAt, String role,
        List<String> permissions) implements Serializable {}
