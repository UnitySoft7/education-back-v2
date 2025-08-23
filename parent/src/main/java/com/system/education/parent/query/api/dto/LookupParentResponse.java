package com.system.education.parent.query.api.dto;

import com.system.education.parent.query.api.response.ParentResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup parent response")
public record LookupParentResponse(
        boolean success, ParentResponse parentResponse) implements Serializable {}
