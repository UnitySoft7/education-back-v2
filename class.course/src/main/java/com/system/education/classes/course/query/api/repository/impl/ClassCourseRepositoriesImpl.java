package com.system.education.classes.course.query.api.repository.impl;

import com.system.education.classes.course.core.model.ClassCourse;
import com.system.education.classes.course.query.api.repository.ClassCourseRepositories;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class ClassCourseRepositoriesImpl implements ClassCourseRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public ClassCourseRepositoriesImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<ClassCourse> getLastClassCourse() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "classCourseCode"));
        query.limit(1);

        return reactiveMongoTemplate.find(query, ClassCourse.class)
                .next();
    }
}
