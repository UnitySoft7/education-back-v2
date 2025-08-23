package com.system.education.permission.permission.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Name response")
public record NameResponse(String code, String name) implements Serializable {}
