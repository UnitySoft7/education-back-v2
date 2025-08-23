package com.system.education.auth.role.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "roles")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Role response")
public class Role implements Serializable {
    @Id
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String roleId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String roleName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String permissions;
    private String enterpriseName;
    private String enterpriseCode;
}
