package com.system.education.skill.detail.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Skill detail response")
public record SkillDetailResponse(
        String skillDetailId, String skillCode, String skillName, String studentCode,
        String studentName, String teacherComment, String teacherCode, String teacherName,
        String rating, String semester, String schoolYear, String establishmentCode,
        String establishmentName, String logCreatedAt, String logCreatedMonth,
        String logCreatedYear, String logCreatedDate) implements Serializable {}
