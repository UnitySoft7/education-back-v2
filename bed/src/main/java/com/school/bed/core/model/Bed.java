package com.school.bed.core.model;



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
@Document(collection = "bed")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Bed response")
public class Bed {
    @Id
    private String bedId;
    private String sigle;
    private String code;



}
