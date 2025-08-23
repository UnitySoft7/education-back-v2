package com.school.schedule.query.api.repository.impl;

import com.school.schedule.core.model.Schedule;
import com.school.schedule.query.api.repository.ScheduleRepositories;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class ScheduleRepositoriesImpl implements ScheduleRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public ScheduleRepositoriesImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }
    @Override
    public
    Mono<Schedule> getLastSchedule ( ) {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "code"));
        query.limit(1);
        return reactiveMongoTemplate.find(query, Schedule.class)
                .next();
    }
}
