package com.system.education.course.teacher.core.dto;

import java.io.Serializable;

public record MessageResponse(boolean success, String message) implements Serializable {}
