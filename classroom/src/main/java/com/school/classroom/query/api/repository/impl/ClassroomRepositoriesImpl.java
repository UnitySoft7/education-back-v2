package com.school.classroom.query.api.repository.impl;

import com.school.classroom.core.model.Classroom;
import com.school.classroom.query.api.repository.ClassroomRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
@RequiredArgsConstructor
@Repository
public class ClassroomRepositoriesImpl implements ClassroomRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;
    @Override
    public Mono<Classroom> getLastClassroom() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "code")); //champ correct
        query.limit(1);
        return reactiveMongoTemplate.find(query, Classroom.class).next();
    }
}
