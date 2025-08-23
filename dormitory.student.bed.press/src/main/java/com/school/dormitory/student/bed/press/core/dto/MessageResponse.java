package com.school.dormitory.student.bed.press.core.dto;

import java.io.Serializable;

public record MessageResponse(boolean success, String message) implements Serializable {}