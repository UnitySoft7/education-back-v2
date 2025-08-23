package com.school.evaluation.query.api.dto;
import com.school.evaluation.query.api.response.EvaluationResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
@Schema(name = "Lookup Evaluation response")
public record LookupEvaluationResponse(boolean success, EvaluationResponse clothResponse) implements Serializable {}
