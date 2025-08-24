package com.school.presence.student.daily.query.api.handler.impl;

import com.school.presence.student.daily.cmd.api.command.PresenceStudentDailyCreatedCommand;
import com.school.presence.student.daily.core.common.LogCreated;
import com.school.presence.student.daily.core.model.PresenceStudentDaily;
import com.school.presence.student.daily.core.payload.PresenceStudentDailyPayload;
import com.school.presence.student.daily.query.api.handler.PresenceStudentDailyEventHandler;
import com.school.presence.student.daily.query.api.repository.PresenceStudentDailyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PresenceStudentDailyEventHandlerImpl implements PresenceStudentDailyEventHandler {
    private final  PresenceStudentDailyRepository presenceStudentDailyRepository;
    private final  PresenceStudentDailyPayload  presenceStudentDailyPayload;
    @Override
    public
    Mono<PresenceStudentDaily> create ( PresenceStudentDailyCreatedCommand command ) {
       return presenceStudentDailyPayload.getCode().flatMap(code -> {
                        PresenceStudentDaily value = PresenceStudentDaily.builder()
                                .presenceStudentDailyId(UUID.randomUUID().toString())
                                .code(code)
                                .presenceInClassCode(command.presenceInClassCode())
                                .presence(command.presence())
                                .student(command.student())
                                .presentStatus(command.status())
                                .prof(command.prof())
                                .scheduleCode(command.scheduleCode())
                                .absents(command.absents())
                                .presents(command.presents())
                                .schoolYear(command.schoolYear())
                                .trimester(command.trimester())
                                .status(command.status())
                                .logCreatedAt(LogCreated.At())
                                .build();
                        return presenceStudentDailyRepository.save(value);
                    });
                }



}
