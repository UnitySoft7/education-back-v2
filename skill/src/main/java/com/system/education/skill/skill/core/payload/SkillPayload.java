package com.system.education.skill.skill.core.payload;

import reactor.core.publisher.Mono;

public interface SkillPayload {
    Mono<String> getCode();

    Mono<Boolean> isSkillCodeExist(String skillCode);
}
