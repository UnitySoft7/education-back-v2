package com.school.dormitory.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
@Schema(name = "Category Response")
public record CategoryResponse(
        String categoryName
) implements Serializable {}
