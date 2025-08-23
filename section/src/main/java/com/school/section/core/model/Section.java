package com.school.section.core.model;

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
@Document(collection = "section")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Section response")
public class Section {
    @Id
    private String sectionId;
    private String name;
    private String sectionCode;
    private String frName;
    private String enName;
    private String establishmentName;
    private String establishmentCode;
}
