package com.school.presence.teacher.query.api.repository.impl;

import com.school.presence.teacher.core.model.PresenceTeacher;
import com.school.presence.teacher.query.api.repository.PresenceTeacherRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
@RequiredArgsConstructor
@Repository
public class PresenceTeacherRepositoriesImpl implements PresenceTeacherRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Mono<PresenceTeacher> getLastPresenceTeacher() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "presenceTeacherCode"));
        query.limit(1);
        return reactiveMongoTemplate.find(query, PresenceTeacher.class)
                .next();
    }
}