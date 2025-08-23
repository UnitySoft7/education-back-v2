package com.system.education.parent.query.api.dto;

import com.system.education.parent.query.api.response.ParentResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All lookup parent response")
public record AllLookupParentResponse(
        boolean success, List<ParentResponse> parentResponses) implements Serializable {}
