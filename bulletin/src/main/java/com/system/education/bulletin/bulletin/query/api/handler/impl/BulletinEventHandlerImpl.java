package com.system.education.bulletin.bulletin.query.api.handler.impl;

import com.system.education.bulletin.bulletin.cmd.api.command.BulletinCreatedCommand;
import com.system.education.bulletin.bulletin.core.model.Bulletin;
import com.system.education.bulletin.bulletin.core.payload.BulletinPayload;
import com.system.education.bulletin.bulletin.query.api.handler.BulletinEventHandler;
import com.system.education.bulletin.bulletin.query.api.repository.BulletinRepository;
import com.system.education.bulletin.core.common.LogCreated;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BulletinEventHandlerImpl implements BulletinEventHandler {
    private final BulletinRepository bulletinRepository;
    private final BulletinPayload bulletinPayload;

    @Override
    public Mono<Bulletin> create(BulletinCreatedCommand command) {
        return bulletinPayload.getCode()
                .flatMap(code -> {
                    Bulletin bulletin = Bulletin.builder()
                            .bulletinId(UUID.randomUUID().toString())
                            .bulletinCode(code)
                            .studentCode(command.studentCode())
                            .studentName(command.studentName())
                            .dateOfBirth(command.dateOfBirth())
                            .daysPresent(command.daysPresent())
                            .daysAbsent(command.daysAbsent())
                            .daysSick(command.daysSick())
                            .otherReason(command.otherReason())
                            .percent(command.percent())
                            .grade(command.grade())
                            .homeRoomTeacherCode(command.homeRoomTeacherCode())
                            .homeRoomTeacherName(command.homeRoomTeacherName())
                            .homeRoomTeacherComment(command.homeRoomTeacherComment())
                            .semester(command.semester())
                            .schoolYear(command.schoolYear())
                            .classCode(command.classCode())
                            .className(command.className())
                            .establishmentCode(command.establishmentCode())
                            .establishmentName(command.establishmentName())
                            .logCreatedAt(LogCreated.At())
                            .logCreatedMonth(LogCreated.Month())
                            .logCreatedYear(LogCreated.Year())
                            .logCreatedDate(LogCreated.Date())
                            .build();
                    return bulletinRepository.save(bulletin);
                });

    }
}
