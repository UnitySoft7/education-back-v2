package com.system.education.skill.skill.query.api.repository;

import com.system.education.skill.skill.core.model.Skill;
import reactor.core.publisher.Mono;

public interface SkillRepositories {
    Mono<Skill> getLastSkill();
}
