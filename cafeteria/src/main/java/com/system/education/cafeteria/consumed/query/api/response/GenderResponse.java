package com.system.education.cafeteria.consumed.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Gender response")
public record GenderResponse(String gender) implements Serializable {}
