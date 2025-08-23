package com.system.education.student.query.api.dto;

import com.system.education.student.query.api.response.GenderResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All lookup gender response")
public record AllLookupGenderResponse(
        boolean success, List<GenderResponse> genderResponses) implements Serializable {}
