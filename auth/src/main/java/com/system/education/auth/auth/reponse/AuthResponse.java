package com.system.education.auth.auth.reponse;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Auth response")
public record AuthResponse(String accessToken) implements Serializable {}
