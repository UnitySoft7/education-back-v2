package com.system.education.auth.auth.command;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Logout command")
public record LogoutCommand(String token) implements Serializable {}
