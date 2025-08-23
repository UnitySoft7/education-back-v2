package com.system.education.infirmary.diagnosis.query.api.dto;

import com.system.education.infirmary.diagnosis.query.api.response.DiagnosisResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All lookup diagnosis response")
public record AllLookupDiagnosisResponse(
        boolean success, List<DiagnosisResponse> diagnosisResponses) implements Serializable {}
