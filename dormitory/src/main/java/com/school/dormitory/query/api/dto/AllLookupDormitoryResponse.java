package com.school.dormitory.query.api.dto;

import com.school.dormitory.query.api.response.DormitoryResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup Dormitory Response")
public record AllLookupDormitoryResponse(boolean success, List<DormitoryResponse> DormitoryResponses) implements Serializable {}
