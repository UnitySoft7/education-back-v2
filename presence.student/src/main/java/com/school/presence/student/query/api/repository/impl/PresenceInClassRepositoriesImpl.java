package com.school.presence.student.query.api.repository.impl;

import com.school.presence.student.core.model.PresenceInClass;
import com.school.presence.student.query.api.repository.PresenceInClassRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
@RequiredArgsConstructor
@Repository
public class PresenceInClassRepositoriesImpl implements PresenceInClassRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Mono<PresenceInClass> getLastPresenceInClass() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "presenceInClassCode"));
        query.limit(1);
        return reactiveMongoTemplate.find(query, PresenceInClass.class)
                .next();
    }
}