package com.school.press.query.api.dto;

import com.school.press.query.api.response.PressResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup Press Response")
public record AllLookupPressResponse(boolean success, List<PressResponse> PressResponses) implements Serializable {}
