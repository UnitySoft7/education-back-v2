package com.system.education.cafeteria.core.dto;

import java.io.Serializable;

public record MessageResponse(boolean success, String message) implements Serializable {}
