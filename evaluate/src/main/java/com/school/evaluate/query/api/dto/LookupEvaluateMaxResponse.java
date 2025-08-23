package com.school.evaluate.query.api.dto;

import com.school.evaluate.query.api.response.EvaluateMaxResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup Evaluate Max response")
public record LookupEvaluateMaxResponse(boolean success, EvaluateMaxResponse evaluateMaxResponse, String msg) implements Serializable {}
