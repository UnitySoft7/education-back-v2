package com.system.education.teacher.query.api.repository.impl;

import com.system.education.teacher.core.model.Teacher;
import com.system.education.teacher.query.api.repository.TeacherRepositories;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class TeacherRepositoriesImpl implements TeacherRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public TeacherRepositoriesImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<Teacher> getLastTeacher() {
        Query query = new Query();
//        query.addCriteria(Criteria.where("id").exists(true));
        query.with(Sort.by(Sort.Direction.DESC, "teacherCode"));
        query.limit(1);

        return reactiveMongoTemplate.find(query, Teacher.class)
                .next();
    }
}
