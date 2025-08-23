package com.system.education.skill.detail.core.model;

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
@Document(collection = "skillDetail")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Skill detail response")
public class SkillDetail {
    @Id
    private String skillDetailId;
    private String skillCode;
    private String skillName;
    private String studentCode;
    private String studentName;
    private String teacherComment;
    private String teacherCode;
    private String teacherName;
    private String rating;
    private String semester;
    private String schoolYear;
    private String establishmentCode;
    private String establishmentName;
    private String logCreatedAt;
    private String logCreatedMonth;
    private String logCreatedYear;
    private String logCreatedDate;
}
