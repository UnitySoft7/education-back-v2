package com.system.education.infirmary.diagnosis.query.api.dto;

import com.system.education.infirmary.diagnosis.query.api.response.DiagnosisResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup diagnosis response")
public record LookupDiagnosisResponse(
        boolean success, DiagnosisResponse diagnosisResponse) implements Serializable {}
