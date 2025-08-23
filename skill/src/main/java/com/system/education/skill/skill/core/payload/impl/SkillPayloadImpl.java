package com.system.education.skill.skill.core.payload.impl;

import com.system.education.skill.core.common.SkillCode;
import com.system.education.skill.skill.core.payload.SkillPayload;
import com.system.education.skill.skill.query.api.repository.SkillRepositories;
import com.system.education.skill.skill.query.api.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SkillPayloadImpl implements SkillPayload {
    private final SkillRepository skillRepository;
    private final SkillRepositories skillRepositories;

    /**
     * This method generates a unique code for the skill.
     * It first checks if there are any existing skills in the repository.
     * If there are no skills, it returns a default code "ESSKC10000001".
     * If there are existing skills, it retrieves the last skill's code and generates a new code based on it.
     *
     * @return A Mono containing the generated skill code.
     */
    @Override
    public Mono<String> getCode(){
        return skillRepository.count().flatMap(aLong -> {
            if (aLong == 0) {
                return Mono.just("ESSKC10000001");
            }
            else {
                Mono<String> code = skillRepositories.getLastSkill()
                        .flatMap(skill -> Mono.just(skill.getSkillCode()));
                return SkillCode.generate(code);
            }
        });
    }

    /**
     * This method checks if a skill with the given code exists in the repository.
     * It uses the skillRepository to check for existence.
     *
     * @param skillCode The skill code of the skill to check.
     * @return A Mono containing true if the skill exists, false otherwise.
     */
    @Override
    public Mono<Boolean> isSkillCodeExist(String skillCode) {
        return skillRepository.existsBySkillCode(skillCode);
    }
}
