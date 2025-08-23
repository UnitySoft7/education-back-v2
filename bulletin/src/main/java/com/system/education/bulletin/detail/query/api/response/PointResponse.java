package com.system.education.bulletin.detail.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Point response")
public record PointResponse(
        String studentCode, String studentName, String average,
        String gradeCode, String gradeName) implements Serializable {}
