package com.system.education.skill.skill.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Skill response")
public record SkillResponse(
        String skillId, String skillCode,
        String skillName, String establishmentCode,
        String establishmentName) implements Serializable {}
