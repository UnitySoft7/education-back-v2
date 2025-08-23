package com.system.education.skill.skill.query.api.repository;

import com.system.education.skill.skill.core.model.Skill;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SkillRepository extends ReactiveMongoRepository<Skill, String> {
    Mono<Skill> findBySkillCode(@Param("skillCode") String skillCode);
    Mono<Boolean> existsBySkillCode(@Param("skillCode") String skillCode);
    Flux<Skill> findByEstablishmentCode(@Param("establishmentCode") String establishmentCode);
}
