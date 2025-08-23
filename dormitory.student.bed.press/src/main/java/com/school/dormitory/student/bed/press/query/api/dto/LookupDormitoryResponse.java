package com.school.dormitory.student.bed.press.query.api.dto;

import com.school.dormitory.student.bed.press.query.api.response.DormitoryResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup Dormitory response")
public record LookupDormitoryResponse(boolean success, DormitoryResponse dormitoryResponse) implements Serializable {}
