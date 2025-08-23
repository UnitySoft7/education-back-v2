package com.school.dormitory.query.api.dto;
import com.school.dormitory.query.api.response.DormitoryResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
@Schema(name = "Lookup Dormitory response")
public record LookupDormitoryResponse(boolean success, DormitoryResponse clothResponse) implements Serializable {}
