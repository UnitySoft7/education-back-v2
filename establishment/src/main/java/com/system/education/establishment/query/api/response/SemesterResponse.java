package com.system.education.establishment.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Response for Trimester")
public record SemesterResponse(String code, String frName, String enName) implements Serializable {}
