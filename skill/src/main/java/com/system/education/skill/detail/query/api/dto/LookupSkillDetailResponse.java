package com.system.education.skill.detail.query.api.dto;

import com.system.education.skill.detail.query.api.response.SkillDetailResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup skill detail response")
public record LookupSkillDetailResponse(
        boolean success, SkillDetailResponse skillDetailResponse) implements Serializable {}
