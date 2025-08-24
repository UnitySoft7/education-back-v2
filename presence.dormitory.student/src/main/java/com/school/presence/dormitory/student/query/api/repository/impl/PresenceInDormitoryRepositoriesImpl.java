package com.school.presence.dormitory.student.query.api.repository.impl;

import com.school.presence.dormitory.student.core.model.PresenceInDormitory;
import com.school.presence.dormitory.student.query.api.repository.PresenceInDormitoryRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
@RequiredArgsConstructor
@Repository
public class PresenceInDormitoryRepositoriesImpl implements PresenceInDormitoryRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Mono<PresenceInDormitory> getLastPresenceInDormitory() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "presenceInDormitoryCode"));
        query.limit(1);
        return reactiveMongoTemplate.find(query, PresenceInDormitory.class)
                .next();
    }
}