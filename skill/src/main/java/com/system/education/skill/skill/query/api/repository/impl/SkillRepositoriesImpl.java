package com.system.education.skill.skill.query.api.repository.impl;

import com.system.education.skill.skill.core.model.Skill;
import com.system.education.skill.skill.query.api.repository.SkillRepositories;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class SkillRepositoriesImpl implements SkillRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public SkillRepositoriesImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<Skill> getLastSkill() {
        Query query = new Query();
//        query.addCriteria(Criteria.where("id").exists(true));
        query.with(Sort.by(Sort.Direction.DESC, "skillCode"));
        query.limit(1);

        return reactiveMongoTemplate.find(query, Skill.class)
                .next();
    }
}
