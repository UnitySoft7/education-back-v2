package com.system.education.skill.skill.core.model;

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
@Document(collection = "skill")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Skill type response")
public class Skill {
    @Id
    private String skillId;
    private String skillCode;
    private String skillName;
    private String establishmentCode;
    private String establishmentName;
}
