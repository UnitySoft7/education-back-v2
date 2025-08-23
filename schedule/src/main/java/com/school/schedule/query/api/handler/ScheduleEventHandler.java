package com.school.schedule.query.api.handler;

import com.school.schedule.cmd.api.command.ScheduleCreatedCommand;
import com.school.schedule.cmd.api.command.ScheduleUpdatedCommand;
import com.school.schedule.cmd.api.command.ScheduleUpdatedCourseCommand;
import com.school.schedule.core.model.Schedule;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public
interface ScheduleEventHandler {
    Mono<Schedule> create(ScheduleCreatedCommand command);

    Mono<Schedule> updateSettings(ScheduleUpdatedCommand command);
}