package com.school.evaluate.query.api.dto;

import com.school.evaluate.query.api.response.EvaluateResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup Evaluate Response")
public record AllLookupEvaluateResponse(boolean success, List<EvaluateResponse> evaluateResponses,
                                        String msg) implements Serializable {
}
