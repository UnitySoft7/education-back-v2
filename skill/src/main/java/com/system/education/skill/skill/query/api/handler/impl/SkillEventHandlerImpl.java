package com.system.education.skill.skill.query.api.handler.impl;

import com.system.education.skill.skill.cmd.api.command.SkillCreatedCommand;
import com.system.education.skill.skill.cmd.api.command.SkillUpdatedCommand;
import com.system.education.skill.skill.core.model.Skill;
import com.system.education.skill.skill.core.payload.SkillPayload;
import com.system.education.skill.skill.query.api.handler.SkillEventHandler;
import com.system.education.skill.skill.query.api.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SkillEventHandlerImpl implements SkillEventHandler {
    private final SkillRepository skillRepository;
    private final SkillPayload skillPayload;

    @Override
    public Mono<Skill> create(SkillCreatedCommand command) {
        return skillPayload.getCode()
                .flatMap(code -> {
                    Skill skill = Skill.builder()
                            .skillId(UUID.randomUUID().toString())
                            .skillCode(code)
                            .skillName(command.skillName())
                            .establishmentCode(command.establishmentCode())
                            .establishmentName(command.establishmentName())
                            .build();
                    return skillRepository.save(skill);
                });

    }

    @Override
    public Mono<Skill> update(SkillUpdatedCommand command) {
        return skillRepository.findBySkillCode(command.skillCode())
                .flatMap(product -> {
                    product.setSkillName(command.skillName());
                    return skillRepository.save(product);
                });
    }
}
