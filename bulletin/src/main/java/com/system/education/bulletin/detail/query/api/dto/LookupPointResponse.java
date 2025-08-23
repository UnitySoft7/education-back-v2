package com.system.education.bulletin.detail.query.api.dto;

import com.system.education.bulletin.detail.query.api.response.PointResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup point response")
public record LookupPointResponse(
        boolean success, PointResponse pointResponses) implements Serializable {}
