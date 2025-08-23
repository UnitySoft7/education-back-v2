package com.system.education.bulletin.detail.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Rating response")
public record RatingResponse(String code, String name) implements Serializable {}
