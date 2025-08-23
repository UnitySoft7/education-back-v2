package com.system.education.cafeteria.product.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "product")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Product response")
public class Product {
    @Id
    private String productId;
    private String productCode;
    private String productName;
    private double amount;
    private String establishmentCode;
    private String establishmentName;
}
