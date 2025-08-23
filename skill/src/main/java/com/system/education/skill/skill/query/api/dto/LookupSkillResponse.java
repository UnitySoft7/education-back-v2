package com.system.education.skill.skill.query.api.dto;

import com.system.education.skill.skill.query.api.response.SkillResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup skill response")
public record LookupSkillResponse(
        boolean success, SkillResponse skillResponse) implements Serializable {}
