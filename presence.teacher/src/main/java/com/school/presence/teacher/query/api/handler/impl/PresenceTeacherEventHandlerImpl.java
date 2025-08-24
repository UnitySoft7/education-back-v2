package com.school.presence.teacher.query.api.handler.impl;

import com.school.presence.teacher.cmd.api.command.PresenceTeacherCreatedCommand;
import com.school.presence.teacher.core.common.LogCreated;
import com.school.presence.teacher.core.model.PresenceTeacher;
import com.school.presence.teacher.core.payload.PresenceTeacherPayload;
import com.school.presence.teacher.query.api.handler.PresenceTeacherEventHandler;
import com.school.presence.teacher.query.api.repository.PresenceTeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class PresenceTeacherEventHandlerImpl implements PresenceTeacherEventHandler {
    private final  PresenceTeacherRepository presenceTeacherRepository;
    private final  PresenceTeacherPayload presenceTeacherPayload;
    @Override
    public
    Mono<PresenceTeacher> create ( PresenceTeacherCreatedCommand command ) {
       return presenceTeacherPayload.getCode().flatMap(code -> {
                        PresenceTeacher value = PresenceTeacher.builder()
                                .presenceTeacherId (UUID.randomUUID().toString())
                                .code( code )
                                .prof(command.prof())
                                .profName(command.profName())
                                .pointer(command.pointer())
                                .pointerName(command.pointerName())
                                .departHour("17:10 pm")
                                .day("MONDAY")
                                .trimester("1st Trimester")
                                .schoolYear("2025-2026")
                                .logCreatedAt(LogCreated.At())
                                .logCreatedDate(LogCreated.Date())
                                .logCreatedMonth(LogCreated.Month())
                                .logCreatedYear(LogCreated.Year())
                                .build();
                        return presenceTeacherRepository.save(value);
                    });
                }

}
