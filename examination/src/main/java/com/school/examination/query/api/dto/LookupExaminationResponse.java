package com.school.examination.query.api.dto;
import com.school.examination.query.api.response.ExaminationResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
@Schema(name = "Lookup Examination response")
public record LookupExaminationResponse(boolean success, ExaminationResponse examinationResponse) implements Serializable {}
