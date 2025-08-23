package com.school.schedule.query.api.repository;

import com.school.schedule.core.model.Schedule;
import reactor.core.publisher.Mono;

public
interface ScheduleRepositories {
    Mono<Schedule> getLastSchedule ( );
}
