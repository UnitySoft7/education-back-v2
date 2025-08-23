package com.school.press.core.model;

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
@Document(collection = "press")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Press response")
public class Press {
    @Id
    private String pressId;
    private String sigle;
    private String code;
}
