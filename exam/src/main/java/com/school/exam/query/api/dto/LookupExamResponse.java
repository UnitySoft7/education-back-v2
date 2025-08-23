package com.school.exam.query.api.dto;
import com.school.exam.query.api.response.ExamResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
@Schema(name = "Lookup Exam response")
public record LookupExamResponse(boolean success, ExamResponse clothResponse) implements Serializable {}
