package com.school.dormitory.student.bed.press.query.api.dto;

import com.school.dormitory.student.bed.press.query.api.response.BedResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
@Schema(name = "Lookup Bed response")
public record LookupBedResponse(boolean success, BedResponse bedResponse) implements Serializable {}
