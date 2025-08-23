package com.school.minos.transaction.core.model;



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
@Document(collection = "transaction")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Transaction response")
public class Transaction {
    @Id
    private String transaId;
    private String transaCode;
    private String minosCode;
    private String studentCode;
    private String studentFullname;
    private String trimester;
    private double amount;
    private double maxamount;
    private String status;
    private String schoolYear;
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
