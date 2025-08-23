package com.system.education.skill.core.dto;

import java.io.Serializable;

public record MessageResponse(boolean success, String message) implements Serializable {}
