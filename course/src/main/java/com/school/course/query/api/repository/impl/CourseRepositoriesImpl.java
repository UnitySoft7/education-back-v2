package com.school.course.query.api.repository.impl;

import com.school.course.core.model.Course;
import com.school.course.query.api.repository.CourseRepositories;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class CourseRepositoriesImpl implements CourseRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public CourseRepositoriesImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }
    @Override
    public
    Mono<Course> getLastCourse ( ) {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "code"));
        query.limit(1);
        return reactiveMongoTemplate.find(query, Course.class).next();}

}



