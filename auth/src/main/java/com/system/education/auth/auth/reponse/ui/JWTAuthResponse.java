package com.system.education.auth.auth.reponse.ui;

import com.system.education.auth.auth.reponse.AuthResponse;
import com.system.education.auth.auth.reponse.AuthUserResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "JWT Auth response")
public record JWTAuthResponse(
        boolean success, AuthResponse auth,
        AuthUserResponse userAuth) implements Serializable {}
