package com.school.examination.query.api.dto;

import com.school.examination.query.api.response.ExaminationResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup Examination Response")
public record AllLookupExaminationResponse(boolean success, List<ExaminationResponse> examinationResponses,String msg) implements Serializable {}
