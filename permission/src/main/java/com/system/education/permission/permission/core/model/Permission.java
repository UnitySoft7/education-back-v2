package com.system.education.permission.permission.core.model;

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
@Document(collection = "permission")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Permission response")
public class Permission {
    @Id
    private String permissionId;
    private String permissionType;
    private String permissionCode;
    private String description;
    private String startOn;
    private String endOn;
    private String studentCode;
    private String studentName;
    private String semester;
    private String schoolYear;
    private String establishmentCode;
    private String establishmentName;
    private String logCreatedAt;
    private String logCreatedMonth;
    private String logCreatedYear;
    private String logCreatedDate;
}
