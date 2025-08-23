package com.system.education.skill.detail.query.api.dto;

import com.system.education.skill.detail.query.api.response.SkillDetailResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All lookup skill detail response")
public record AllLookupSkillDetailResponse(
        boolean success, List<SkillDetailResponse> skillDetailResponses) implements Serializable {}
