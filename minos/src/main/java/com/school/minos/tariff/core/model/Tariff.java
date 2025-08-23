package com.school.minos.tariff.core.model;

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
@Document(collection = "tariff")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Tariff response")
public class Tariff {
    @Id
    private String tariffId;
    private String tariffCode;
    private double amount;
    private String establishmentName;
    private String establishmentCode;
    private String sectionName;
    private String sectionCode;
    private String classroomName;
    private String classroomCode;
    private String logCreatedAt;
    private String logCreatedDate;
    private String logCreatedMonth;
    private String logCreatedYear;



}
