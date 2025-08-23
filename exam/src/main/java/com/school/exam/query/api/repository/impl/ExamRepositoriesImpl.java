package com.school.exam.query.api.repository.impl;

import com.school.exam.core.model.Exam;
import com.school.exam.query.api.repository.ExamRepositories;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
@Repository
public class ExamRepositoriesImpl implements ExamRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;
    public ExamRepositoriesImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }
    @Override
    public
    Mono<Exam> getLastExam ( ) {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "code"));
        query.limit(1);
        return reactiveMongoTemplate.find(query, Exam.class)
                .next();
    }
}
