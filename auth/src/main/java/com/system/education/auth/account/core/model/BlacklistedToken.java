package com.system.education.auth.account.core.model;

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
@Document(collection = "blacklistedToken")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Blacklisted Token response")
public class BlacklistedToken {
    @Id
    private String token;
    private long timestamp;
}
