package com.system.education.student.query.api.repository.impl;

import com.system.education.student.core.model.Student;
import com.system.education.student.query.api.repository.StudentRepositories;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class StudentRepositoriesImpl implements StudentRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public StudentRepositoriesImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<Student> getLastStudent() {
        Query query = new Query();
//        query.addCriteria(Criteria.where("id").exists(true));
        query.with(Sort.by(Sort.Direction.DESC, "studentCode"));
        query.limit(1);

        return reactiveMongoTemplate.find(query, Student.class)
                .next();
    }
}
