package com.school.dormitory.student.bed.press.query.api.dto;


import com.school.dormitory.student.bed.press.query.api.response.PressResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
@Schema(name = "Lookup Press response")
public record LookupPressResponse(boolean success, PressResponse clothResponse) implements Serializable {}
