package com.school.minos.minos.query.api.dto;

import com.school.minos.minos.query.api.response.MinosResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup Minos Response")
public record AllLookupMinosResponse(boolean success, List<MinosResponse> minosResponses) implements Serializable {}
