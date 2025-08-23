package com.system.education.auth.role.query.api.dto;

import com.system.education.auth.role.query.api.response.NameResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All lookup names response")
public record AllLookupNameResponse(boolean success, List<NameResponse> nameResponses) implements Serializable {}