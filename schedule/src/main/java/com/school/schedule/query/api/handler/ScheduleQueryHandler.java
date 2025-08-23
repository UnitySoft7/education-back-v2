package com.school.schedule.query.api.handler;


import com.school.schedule.cmd.api.query.FindByCodeQuery;
import com.school.schedule.query.api.response.ScheduleResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ScheduleQueryHandler {
    Flux<ScheduleResponse> findSchedules();

    Mono<ScheduleResponse> findScheduleByScheduleCode(String code);

    Flux<ScheduleResponse> findAllScheduleByClassCode(FindByCodeQuery query);
}