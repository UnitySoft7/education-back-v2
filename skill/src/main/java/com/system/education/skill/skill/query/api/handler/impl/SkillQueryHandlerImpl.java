package com.system.education.skill.skill.query.api.handler.impl;

import com.system.education.skill.skill.core.model.Skill;
import com.system.education.skill.skill.query.api.handler.SkillQueryHandler;
import com.system.education.skill.skill.query.api.query.SkillByCodeQuery;
import com.system.education.skill.skill.query.api.query.SkillByIdQuery;
import com.system.education.skill.skill.query.api.repository.SkillRepository;
import com.system.education.skill.skill.query.api.response.SkillResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SkillQueryHandlerImpl implements SkillQueryHandler {
    private final SkillRepository skillRepository;

    /**
     * This method is used to get all skills
     * @return a flux of skill response
     */
    @Override
    public Flux<SkillResponse> getSkills() {
        return skillRepository.findAll()
                .flatMap(this::getSkillResponse);
    }

    /**
     * This method is used to get a skill by ID
     * @param query the ID of the skill
     * @return a mono of skill response
     */
    @Override
    public Mono<SkillResponse> getSkillById(SkillByIdQuery query) {
        return skillRepository.findById(query.id())
                .flatMap(this::getSkillResponse);
    }

    /**
     * This method is used to get a skill by skill code
     * @param query the skill code of the skill
     * @return a mono of skill response
     */
    @Override
    public Mono<SkillResponse> getSkillByCode(SkillByCodeQuery query) {
        return skillRepository.findBySkillCode(query.code())
                .flatMap(this::getSkillResponse);
    }

    /**
     * This method is used to get a skill by establishment code
     * @param query the establishment code of the establishment
     * @return a mono of skill response
     */
    @Override
    public Flux<SkillResponse> getSkillByEstablishment(SkillByCodeQuery query) {
        return skillRepository.findByEstablishmentCode(query.code())
                .flatMap(this::getSkillResponse);
    }

    /**
     * This method is used to convert a skill to a skill response
     * @param skill the skill to convert
     * @return the skill response
     */
    private Mono<SkillResponse> getSkillResponse(Skill skill) {
        return Mono.just(
                new SkillResponse(skill.getSkillId(),
                        skill.getSkillCode(), skill.getSkillName(),
                        skill.getEstablishmentCode(), skill.getEstablishmentName())
        );
    }
}
