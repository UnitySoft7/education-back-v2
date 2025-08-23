package com.system.education.parent.query.api.repository.impl;

import com.system.education.parent.core.model.Parent;
import com.system.education.parent.query.api.repository.ParentRepositories;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class ParentRepositoriesImpl implements ParentRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public ParentRepositoriesImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<Parent> getLastParent() {
        Query query = new Query();
//        query.addCriteria(Criteria.where("id").exists(true));
        query.with(Sort.by(Sort.Direction.DESC, "parentCode"));
        query.limit(1);

        return reactiveMongoTemplate.find(query, Parent.class)
                .next();
    }
}
