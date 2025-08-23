package com.school.schedule.query.api.handler.impl;

import com.school.schedule.cmd.api.command.ScheduleCreatedCommand;
import com.school.schedule.cmd.api.command.ScheduleUpdatedCommand;
import com.school.schedule.core.model.Schedule;
import com.school.schedule.core.payload.SchedulePayload;
import com.school.schedule.query.api.handler.ScheduleEventHandler;
import com.school.schedule.query.api.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScheduleEventHandlerImpl implements ScheduleEventHandler {
    private final ScheduleRepository scheduleRepository;
    private final SchedulePayload schedulePayload;

    @Override
    public Mono<Schedule> create(ScheduleCreatedCommand command) {
        return schedulePayload.getCode().flatMap(code -> {
            Schedule value = Schedule.builder().scheduleId(UUID.randomUUID().toString())
                    .code(code).index(command.index())
                    .classroomCode(command.classroomCode())
                    .classroomName(command.classroomName())
                    .sectionCode(command.sectionCode())
                    .sectionName(command.sectionName())
                    .establishmentCode(command.establishmentCode())
                    .establishmentName(command.establishmentName())
                    .monday(command.monday()).tuesday(command.tuesday())
                    .wednesday(command.wednesday()).thusday(command.thusday())
                    .friday(command.friday()).saturday(command.saturday()).sunday(command.sunday()).build();
            return scheduleRepository.save(value);
        });
    }

    @Override
    public Mono<Schedule> updateSettings(ScheduleUpdatedCommand command) {
        return scheduleRepository.findScheduleByCode(command.code()).flatMap(schedule -> {
            schedule.setIndex(command.index());
            schedule.setClassroomCode(command.classroomCode());
            schedule.setClassroomCode(command.classroomCode());
            schedule.setClassroomName(command.classroomName());
            schedule.setEstablishmentName(command.establishmentName());
            schedule.setEstablishmentCode(command.establishmentCode());
            schedule.setSectionName(command.sectionName());
            schedule.setSectionCode(command.sectionCode());
            schedule.setClassroomName(command.classroomName());
            schedule.setMonday(command.monday());
            schedule.setTuesday(command.tuesday());
            schedule.setWednesday(command.wednesday());
            schedule.setThusday(command.thusday());
            schedule.setFriday(command.friday());
            schedule.setSaturday(command.saturday());
            schedule.setSunday(command.sunday());

            return scheduleRepository.save(schedule);
        });
    }
}


