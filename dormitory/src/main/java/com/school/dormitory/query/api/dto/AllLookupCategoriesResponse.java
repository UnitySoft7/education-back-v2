package com.school.dormitory.query.api.dto;

import com.school.dormitory.query.api.response.CategoryResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;
@Schema(name = "All Lookup Categories Response")
public record AllLookupCategoriesResponse(
        boolean success,
        List<CategoryResponse> categoryResponses
) implements Serializable {}
