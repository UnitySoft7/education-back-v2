package com.system.education.skill.skill.query.api.handler;

import com.system.education.skill.skill.query.api.query.SkillByCodeQuery;
import com.system.education.skill.skill.query.api.query.SkillByIdQuery;
import com.system.education.skill.skill.query.api.response.SkillResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SkillQueryHandler {
    Flux<SkillResponse> getSkills();
    Mono<SkillResponse> getSkillById(SkillByIdQuery query);
    Mono<SkillResponse> getSkillByCode(SkillByCodeQuery query);
    Flux<SkillResponse> getSkillByEstablishment(SkillByCodeQuery query);
}
