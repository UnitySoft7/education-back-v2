package com.system.education.course.teacher.query.api.repository.impl;

import com.system.education.course.teacher.core.model.CourseTeacher;
import com.system.education.course.teacher.query.api.repository.CourseTeacherRepositories;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class CourseTeacherRepositoriesImpl implements CourseTeacherRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public CourseTeacherRepositoriesImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<CourseTeacher> getLastEstablishmentSectionClassCourse() {
        Query query = new Query();
//        query.addCriteria(Criteria.where("id").exists(true));
        query.with(Sort.by(Sort.Direction.DESC, "courseTeacherCode"));
        query.limit(1);

        return reactiveMongoTemplate.find(query, CourseTeacher.class)
                .next();
    }
}
