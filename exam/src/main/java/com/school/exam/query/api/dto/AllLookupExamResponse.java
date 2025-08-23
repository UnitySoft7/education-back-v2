package com.school.exam.query.api.dto;

import com.school.exam.query.api.response.ExamResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup Exam Response")
public record AllLookupExamResponse(boolean success, List<ExamResponse> examResponses, String msg) implements Serializable {}
