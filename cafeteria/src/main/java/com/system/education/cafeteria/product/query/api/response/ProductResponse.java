package com.system.education.cafeteria.product.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Product response")
public record ProductResponse(
        String productId, String productCode, String productName, double amount,
        String establishmentCode, String establishmentName) implements Serializable {}
