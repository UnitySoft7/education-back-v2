package com.system.education.skill.detail.query.api.handler;

import com.system.education.skill.detail.cmd.api.command.SkillDetailCreatedCommand;
import com.system.education.skill.detail.cmd.api.command.SkillDetailUpdatedCommand;
import com.system.education.skill.detail.core.model.SkillDetail;
import reactor.core.publisher.Mono;

public interface SkillDetailEventHandler {
    Mono<SkillDetail> create(SkillDetailCreatedCommand command);

    Mono<SkillDetail> update(SkillDetailUpdatedCommand command);
}
