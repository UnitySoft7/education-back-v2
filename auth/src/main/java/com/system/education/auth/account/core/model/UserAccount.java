package com.system.education.auth.account.core.model;

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
@Document(collection = "userAccount")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "User account response")
public class UserAccount implements Serializable {
    @Id
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userAccountId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String fullName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String username;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String role;
    private String establishmentName;
    private String establishmentCode;
}
