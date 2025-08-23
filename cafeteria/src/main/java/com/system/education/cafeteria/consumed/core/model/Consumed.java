package com.system.education.cafeteria.consumed.core.model;

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
@Document(collection = "consumed")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Consumed response")
public class Consumed {
    @Id
    private String consumedId;
    private String productCode;
    private String productName;
    private String qty;
    private double amount;
    private String studentCode;
    private String studentName;
    private String employeeCode;
    private String employeeName;
    private String establishmentCode;
    private String establishmentName;
    private String semester;
    private String schoolYear;
    private String status;
    private String logCreatedAt;
    private String logCreatedDate;
    private String logCreatedMonth;
    private String logCreatedYear;
}
