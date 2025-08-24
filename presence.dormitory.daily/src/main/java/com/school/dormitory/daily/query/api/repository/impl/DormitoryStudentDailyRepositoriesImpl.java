package com.school.dormitory.daily.query.api.repository.impl;

import com.school.dormitory.daily.core.model.DormitoryStudentDaily;
import com.school.dormitory.daily.query.api.repository.DormitoryStudentDailyRepositories;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class DormitoryStudentDailyRepositoriesImpl implements DormitoryStudentDailyRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public DormitoryStudentDailyRepositoriesImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }
    @Override
    public
    Mono<DormitoryStudentDaily> getLastDormitoryStudentDaily ( ) {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "code"));
        query.limit(1);
        return reactiveMongoTemplate.find(query, DormitoryStudentDaily.class).next();}

}



