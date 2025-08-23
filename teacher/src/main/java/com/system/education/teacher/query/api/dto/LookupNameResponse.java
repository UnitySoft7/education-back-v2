package com.system.education.teacher.query.api.dto;

import com.system.education.teacher.query.api.response.NameResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "Lookup name response")
public record LookupNameResponse(
        boolean success, List<NameResponse> nameResponses) implements Serializable {}
