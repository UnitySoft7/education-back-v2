package com.system.education.auth.core.dto;

import java.io.Serializable;

public record MessageResponse(boolean success, String message) implements Serializable {}