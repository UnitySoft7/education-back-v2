package com.school.evaluate.query.api.dto;
import com.school.evaluate.query.api.response.EvaluateResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
@Schema(name = "Lookup Evaluate response")
public record LookupEvaluateResponse(boolean success, EvaluateResponse evaluateResponse,String msg) implements Serializable {}
