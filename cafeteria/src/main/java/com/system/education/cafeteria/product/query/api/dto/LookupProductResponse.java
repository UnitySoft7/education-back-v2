package com.system.education.cafeteria.product.query.api.dto;

import com.system.education.cafeteria.product.query.api.response.ProductResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup product response")
public record LookupProductResponse(
        boolean success, ProductResponse productResponse) implements Serializable {}
