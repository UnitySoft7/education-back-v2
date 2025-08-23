package com.system.education.cafeteria.product.query.api.dto;

import com.system.education.cafeteria.product.query.api.response.ProductResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All lookup product response")
public record AllLookupProductResponse(
        boolean success, List<ProductResponse> productResponses) implements Serializable {}
