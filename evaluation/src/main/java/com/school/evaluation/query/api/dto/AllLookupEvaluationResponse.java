package com.school.evaluation.query.api.dto;

import com.school.evaluation.query.api.response.EvaluationResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup Evaluation Response")
public record AllLookupEvaluationResponse(boolean success, List<EvaluationResponse> evaluationResponses,String msg) implements Serializable {}
