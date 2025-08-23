package com.system.education.skill.skill.query.api.handler;

import com.system.education.skill.skill.cmd.api.command.SkillCreatedCommand;
import com.system.education.skill.skill.cmd.api.command.SkillUpdatedCommand;
import com.system.education.skill.skill.core.model.Skill;
import reactor.core.publisher.Mono;

public interface SkillEventHandler {
    Mono<Skill> create(SkillCreatedCommand command);

    Mono<Skill> update(SkillUpdatedCommand command);
}
