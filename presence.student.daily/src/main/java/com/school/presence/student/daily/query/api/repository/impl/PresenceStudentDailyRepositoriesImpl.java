package com.school.presence.student.daily.query.api.repository.impl;

import com.school.presence.student.daily.core.model.PresenceStudentDaily;
import com.school.presence.student.daily.query.api.repository.PresenceStudentDailyRepositories;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class PresenceStudentDailyRepositoriesImpl implements PresenceStudentDailyRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public PresenceStudentDailyRepositoriesImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }
    @Override
    public
    Mono<PresenceStudentDaily> getLastPresenceStudentDaily ( ) {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "code"));
        query.limit(1);
        return reactiveMongoTemplate.find(query, PresenceStudentDaily.class).next();}

}



