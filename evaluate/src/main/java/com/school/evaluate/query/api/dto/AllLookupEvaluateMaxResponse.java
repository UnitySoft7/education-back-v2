package com.school.evaluate.query.api.dto;

import com.school.evaluate.query.api.response.EvaluateMaxResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup Evaluate Max response")
public record AllLookupEvaluateMaxResponse(
        boolean success,
        List<EvaluateMaxResponse> evaluateMaxResponses,
        String ESCS,
        String fullname,
        double noteByPonderation,
        double ponderationMax,
        double sommeNote,
        double sommeMax,
        double pourcentage,
        String msg
) implements Serializable {}
