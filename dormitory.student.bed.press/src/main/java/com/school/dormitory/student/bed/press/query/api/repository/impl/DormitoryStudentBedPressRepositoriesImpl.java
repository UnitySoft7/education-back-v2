package com.school.dormitory.student.bed.press.query.api.repository.impl;

import com.school.dormitory.student.bed.press.core.model.DormitoryStudentBedPress;
import com.school.dormitory.student.bed.press.query.api.repository.DormitoryStudentBedPressRepositories;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class DormitoryStudentBedPressRepositoriesImpl implements DormitoryStudentBedPressRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public DormitoryStudentBedPressRepositoriesImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }
    @Override
    public
    Mono<DormitoryStudentBedPress> getLastDormitoryStudentBedPress ( ) {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "code"));
        query.limit(1);
        return reactiveMongoTemplate.find(query, DormitoryStudentBedPress.class).next();}

}



