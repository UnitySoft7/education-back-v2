package com.system.education.infirmary.consumption.core.model;

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
@Document(collection = "infirmaryConsumption")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Infirmary consumption response")
public class InfirmaryConsumption {
    @Id
    private String consumptionId;
    private String studentCode;
    private String studentName;
    private double consumptionAmount;
    private double payedAmount;
    private String status;
    private String establishmentCode;
    private String establishmentName;
    private String semester;
    private String schoolYear;
    private String logCreatedAt;
    private String logCreatedDate;
    private String logCreatedMonth;
    private String logCreatedYear;
}
