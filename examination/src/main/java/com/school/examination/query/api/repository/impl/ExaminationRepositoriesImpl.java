package com.school.examination.query.api.repository.impl;

import com.school.examination.core.model.Examination;
import com.school.examination.query.api.repository.ExaminationRepositories;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class ExaminationRepositoriesImpl implements ExaminationRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public ExaminationRepositoriesImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }
    @Override
    public
    Mono<Examination> getLastExamination ( ) {
        Query query = new Query();
//        query.addCriteria(Criteria.where("clothCode").exists(true));
        query.with(Sort.by(Sort.Direction.DESC, "code"));
        query.limit(1);
        return reactiveMongoTemplate.find(query, Examination.class)
                .next();
    }
}
