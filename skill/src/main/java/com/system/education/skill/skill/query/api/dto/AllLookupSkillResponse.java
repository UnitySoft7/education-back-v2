package com.system.education.skill.skill.query.api.dto;

import com.system.education.skill.skill.query.api.response.SkillResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All lookup skill response")
public record AllLookupSkillResponse(
        boolean success, List<SkillResponse> skillResponses) implements Serializable {}
