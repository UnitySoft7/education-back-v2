package com.system.education.bulletin.bulletin.query.api.repository.impl;

import com.system.education.bulletin.bulletin.core.model.Bulletin;
import com.system.education.bulletin.bulletin.query.api.repository.BulletinRepositories;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class BulletinRepositoriesImpl implements BulletinRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public BulletinRepositoriesImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<Bulletin> getLastBulletin() {
        Query query = new Query();
//        query.addCriteria(Criteria.where("id").exists(true));
        query.with(Sort.by(Sort.Direction.DESC, "bulletinCode"));
        query.limit(1);

        return reactiveMongoTemplate.find(query, Bulletin.class)
                .next();
    }
}
