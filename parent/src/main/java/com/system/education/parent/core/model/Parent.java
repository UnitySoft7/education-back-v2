package com.system.education.parent.core.model;

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
@Document(collection = "parent")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Parent response")
public class Parent {
    @Id
    private String parentId;
    private String parentCode;
    private String fullName;
    private String citizenId;
    private String phoneNo;
    private String address;
    private String logCreatedAt;
    private String logCreatedMonth;
    private String logCreatedYear;
    private String logCreatedDate;
}
