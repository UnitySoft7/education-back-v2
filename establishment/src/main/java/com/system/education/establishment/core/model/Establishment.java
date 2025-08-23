package com.system.education.establishment.core.model;

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
@Document(collection = "establishment")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Establishment response")
public class Establishment {
    @Id
    private String establishmentId;
    private String establishmentCode;
    private String establishmentName;
    private String nickName;
    private String nif;
    private String rc;
    private String phoneNo;
    private String email;
    private String site;
    private String state;
    private String logoPath;
    private String province;
    private String commune;
    private String zone;
    private String quarter;
    private String locality;
    private String logCreatedAt;
    private String logCreatedMonth;
    private String logCreatedYear;
    private String logCreatedDate;
    private String logModifiedDate;
}
